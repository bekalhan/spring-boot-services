package com.bas.userservice.controller;

import com.bas.userservice.dto.VerificationTokenDto;
import com.bas.userservice.entity.VerificationToken;
import com.bas.userservice.service.VerificationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/token")
@RequiredArgsConstructor
public class VerificationTokenController {
    private final VerificationTokenService verificationTokenService;

    @GetMapping
    public ResponseEntity<List<VerificationTokenDto>> getAllToken(){
        List<VerificationTokenDto> allTokens = verificationTokenService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(allTokens);
    }

    @GetMapping("/{tokenId}")
    public ResponseEntity<VerificationTokenDto> getTokenById(@PathVariable(value="tokenId") Integer tokenId){
        VerificationTokenDto token = this.verificationTokenService.findById(tokenId);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    @PostMapping
    public ResponseEntity<VerificationTokenDto> save(@RequestBody VerificationTokenDto verificationTokenDto){
        VerificationTokenDto token = this.verificationTokenService.save(verificationTokenDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(token);
    }

    @PutMapping
    public ResponseEntity<VerificationTokenDto> update(@RequestBody VerificationTokenDto verificationTokenDto){
        VerificationTokenDto token = this.verificationTokenService.update(verificationTokenDto);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    @PutMapping("/{tokenId}")
    public ResponseEntity<VerificationTokenDto> update(@PathVariable(value="tokenId") Integer tokenId,@RequestBody VerificationTokenDto verificationTokenDto){
        VerificationTokenDto token = this.verificationTokenService.update(tokenId,verificationTokenDto);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    public ResponseEntity<Boolean> deleteById(@PathVariable(value="tokenId") Integer tokenId){
        this.verificationTokenService.deleteById(tokenId);
        return ResponseEntity.ok(true);
    }
}
