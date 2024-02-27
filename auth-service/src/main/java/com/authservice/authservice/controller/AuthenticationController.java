package com.authservice.authservice.controller;

import com.authservice.authservice.entity.User;
import com.authservice.authservice.request.AuthenticationRequest;
import com.authservice.authservice.request.RegisterRequest;
import com.authservice.authservice.request.VerificationRequest;
import com.authservice.authservice.response.AuthenticationResponse;
import com.authservice.authservice.service.AuthenticationService;
import com.authservice.authservice.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

  /*  @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest request
    ){
        var response = authenticationService.register(request);
        if(request.getMfaEnabled()){
            return ResponseEntity.ok(request);

        }
        return ResponseEntity.accepted().build();

    }*/

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        System.out.println("register"+request);
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        System.out.println("authenticate"+request);
        return ResponseEntity.ok(authenticationService.authenticate(request));
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
    public User validateToken(@RequestParam String jwt){
        System.out.println("here"+jwt);
        return jwtService.validateToken(jwt);
    }

}