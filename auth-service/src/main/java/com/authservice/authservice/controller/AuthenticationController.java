package com.authservice.authservice.controller;

import com.authservice.authservice.dto.AuthenticationDTO;
import com.authservice.authservice.entity.User;
import com.authservice.authservice.request.AuthenticationRequest;
import com.authservice.authservice.request.RegisterRequest;
import com.authservice.authservice.request.VerificationRequest;
import com.authservice.authservice.response.AuthenticationResponse;
import com.authservice.authservice.service.AuthenticationService;
import com.authservice.authservice.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @Valid @RequestBody RegisterRequest request
    ){
        System.out.println("register");
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @Valid @RequestBody AuthenticationRequest request
    ){
        System.out.println("login");
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @GetMapping("/test")
    public ResponseEntity<String> test(
    ){
        return ResponseEntity.ok("test");
    }


    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        authenticationService.refreshToken(request,response);
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyCode(
            @RequestBody VerificationRequest verificationRequest
    ){
        return ResponseEntity.ok(authenticationService.verifyCode(verificationRequest));
    }

    @GetMapping("validateTkn")
    public AuthenticationDTO validateToken(@RequestParam String jwt){
        System.out.println("validate");
        var username = jwtService.extractUsername(jwt);
        System.out.println("username = " + username);
        AuthenticationDTO user = authenticationService.findUserByUsername(username,jwt);
        System.out.println("user"+user);
        return user;
    }

}