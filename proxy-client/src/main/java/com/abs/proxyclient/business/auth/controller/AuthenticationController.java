package com.abs.proxyclient.business.auth.controller;

import com.abs.proxyclient.business.auth.model.request.AuthenticationRequest;
import com.abs.proxyclient.business.auth.model.response.AuthenticationResponse;
import com.abs.proxyclient.business.auth.service.AuthenticationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authenticate")
@Slf4j
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody
            @NotNull(message = "")
            @Valid final AuthenticationRequest authenticationRequest) {
        log.info("**Authentication controller, proceed with the request*\n");
        return ResponseEntity.ok(this.authenticationService.authenticate(authenticationRequest));
    }

    @GetMapping("/jwt/{jwt}")
    public ResponseEntity<Boolean> authenticate(@PathVariable("jwt") final String jwt) {
        log.info("**Authentication controller, proceed with the request*\n");
        return ResponseEntity.ok(this.authenticationService.authenticate(jwt));
    }
}
