package com.bas.userservice.controller;

import com.bas.userservice.dto.CredentialsDto;
import com.bas.userservice.service.CredentialsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credential")
@RequiredArgsConstructor
public class CredentialsController {
    private final CredentialsService credentialsService;

    @GetMapping
    public ResponseEntity<List<CredentialsDto>> getAllCredentials(){
        List<CredentialsDto> allCredentials = credentialsService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(allCredentials);
    }

    @GetMapping("/{credentialId}")
    public ResponseEntity<CredentialsDto> getCredentialById(@PathVariable(value="credentialId") Integer credentialId){
        CredentialsDto credential = credentialsService.findById(credentialId);
        return ResponseEntity.status(HttpStatus.OK).body(credential);
    }

    @PostMapping
    public ResponseEntity<CredentialsDto> save(@RequestBody CredentialsDto credentialsDto){
        CredentialsDto credential = credentialsService.save(credentialsDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(credential);
    }

    @PutMapping
    public ResponseEntity<CredentialsDto> update(@RequestBody CredentialsDto credentialsDto){
        CredentialsDto credential = credentialsService.update(credentialsDto);
        return ResponseEntity.status(HttpStatus.OK).body(credential);
    }

    @PutMapping("/{credentialId}")
    public ResponseEntity<CredentialsDto> update(@PathVariable(value="credentialId") Integer credentialId,@PathVariable CredentialsDto credentialsDto){
        CredentialsDto credential = credentialsService.update(credentialId,credentialsDto);
        return ResponseEntity.status(HttpStatus.OK).body(credential);
    }

    public ResponseEntity<Boolean> deleteById(@PathVariable(value="credentialId") Integer credentialId){
        this.credentialsService.deleteById(credentialId);
        return ResponseEntity.ok(true);
    }

}
