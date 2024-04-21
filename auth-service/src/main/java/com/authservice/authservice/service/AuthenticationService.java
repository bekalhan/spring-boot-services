package com.authservice.authservice.service;

import com.authservice.authservice.dto.AuthenticationDTO;
import com.authservice.authservice.dto.UserDTO;
import com.authservice.authservice.entity.Token;
import com.authservice.authservice.entity.User;
import com.authservice.authservice.enumPackage.TokenType;
import com.authservice.authservice.exception.ResourceNotFoundException;
import com.authservice.authservice.exception.UnAuthorizedException;
import com.authservice.authservice.repository.TokenRepository;
import com.authservice.authservice.repository.UserRepository;
import com.authservice.authservice.request.AuthenticationRequest;
import com.authservice.authservice.request.RegisterRequest;
import com.authservice.authservice.request.VerificationRequest;
import com.authservice.authservice.response.AuthenticationResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final TokenRepository tokenRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse register(RegisterRequest request){
        if(userRepository.findByUsername(request.getEmail()).isPresent()) {
            throw new DataIntegrityViolationException("A user with this email already exists");
        }

        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .username(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        User savedUser = userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser,jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .username(request.getEmail())
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword());

        authenticationManager.authenticate(
                usernamePasswordAuthenticationToken
        );

        Optional<User> userDetails = Optional.ofNullable(userRepository.findByUsername(request.getEmail()).orElseThrow(() -> new EntityNotFoundException()));
        var user = userRepository.findByUsername(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user,jwtToken);
        return new AuthenticationResponse().builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .username(request.getEmail())
                .firstname(userDetails.get().getFirstname())
                .lastname(userDetails.get().getLastname())
                .build();
    }

    private void revokeAllUserTokens(User user){
        var validUserTokens = tokenRepository.findByValidTokenByUser(user.getId());
        if(validUserTokens.isEmpty()) return;

        validUserTokens.forEach(t ->{
            t.setExpired(true);
            t.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    private void saveUserToken(User user,String jwtToken){
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .build();

        tokenRepository.save(token);
    }

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.userRepository.findByUsername(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .firstname(user.getFirstname())
                        .lastname(user.getLastname())
                        .username(user.getUsername())
                        .refreshToken(refreshToken)
                        .mfaEnabled(false)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }


    public AuthenticationResponse verifyCode(VerificationRequest verificationRequest) {
        User user = userRepository.findByUsername(verificationRequest.getEmail())
                .orElseThrow(() -> new EntityNotFoundException(String.format("no user found %s"+verificationRequest.getEmail())));

        var jwtToken = jwtService.generateToken(user);
        var refresh = jwtService.generateRefreshToken(user);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refresh)
                .mfaEnabled(user.getMfaEnabled())
                .build()
                ;
    }

    public AuthenticationDTO findUserByUsername(String username,String token){
        UserDTO userDTO = userRepository.findByUsernameAndField(username);

        if (userDTO == null) {
            throw new UnAuthorizedException("User not found");
        }

        AuthenticationDTO authenticationDTO = userRepository.findTokenByUserid(userDTO.getId(), token);

        if (authenticationDTO == null || authenticationDTO.getToken() == null || authenticationDTO.getUsername() == null || authenticationDTO.getPassword() == null) {
            throw new UnAuthorizedException("You have no access to this route");
        }

        return authenticationDTO;
    }

}
