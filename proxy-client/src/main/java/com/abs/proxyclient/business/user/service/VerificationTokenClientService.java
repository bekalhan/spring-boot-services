package com.abs.proxyclient.business.user.service;


import com.abs.proxyclient.business.user.model.VerificationTokenDto;
import com.abs.proxyclient.business.user.model.response.VerificationUserTokenServiceCollectionDtoResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "USER-SERVICE", contextId = "verificationTokenClientService", path = "/user-service/api/verificationTokens", dismiss404 = true)
public interface VerificationTokenClientService {

    @GetMapping
    ResponseEntity<VerificationUserTokenServiceCollectionDtoResponse> findAll();

    @GetMapping("/{verificationTokenId}")
    ResponseEntity<VerificationTokenDto> findById(
            @PathVariable("verificationTokenId")
            @NotBlank(message = "*Input must not blank!**")
            @Valid final String verificationTokenId);

    @PostMapping
    ResponseEntity<VerificationTokenDto> save(
            @RequestBody
            @NotNull(message = "*Input must not NULL!**")
            @Valid final VerificationTokenDto verificationTokenDto);

    @PutMapping
    ResponseEntity<VerificationTokenDto> update(
            @RequestBody
            @NotNull(message = "*Input must not NULL!**")
            @Valid final VerificationTokenDto verificationTokenDto);

    @PutMapping("/{verificationTokenId}")
    ResponseEntity<VerificationTokenDto> update(
            @PathVariable("verificationTokenId")
            @NotBlank(message = "*Input must not blank!**") final String verificationTokenId,
            @RequestBody
            @NotNull(message = "*Input must not NULL!**")
            @Valid final VerificationTokenDto verificationTokenDto);

    @DeleteMapping("/{verificationTokenId}")
    ResponseEntity<Boolean> deleteById(@PathVariable("verificationTokenId") @NotBlank(message = "*Input must not blank!**") @Valid final String verificationTokenId);

}
