package com.authservice.authservice.controller;

import com.authservice.authservice.config.SecurityConfiguration;
import com.authservice.authservice.dto.UserDTO;
import com.authservice.authservice.entity.AuthResponse;
import com.authservice.authservice.entity.User;
import com.authservice.authservice.repository.UserRepository;
import com.authservice.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("jwt/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final SecurityConfiguration jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    @PostMapping("register")
    public ResponseEntity<AuthResponse>signup(@RequestBody UserDTO userDTO){
        User user=User
                .builder()
                .username(userDTO.getUsername())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .build();

        userRepo.save(user);
        String jwt=jwtService.generateToken(user);
        return new ResponseEntity<>(AuthResponse.builder().jwt(jwt).build(), HttpStatus.OK);
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponse> login(@RequestBody UserDTO userDTO){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDTO.getUsername(),userDTO.getPassword())
        );
        User user= (User) userService.loadUserByUsername(userDTO.getUsername());
        String jwt=jwtService.generateToken(user);
        return new ResponseEntity<>(AuthResponse.builder().jwt(jwt).build(),HttpStatus.OK);
    }

   /* @GetMapping("validateTkn")
    public User validateToken(@RequestParam String jwt){
        return jwtService.validateToken(jwt);
    }
*/


}