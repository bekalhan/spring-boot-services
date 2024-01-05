package com.abs.proxyclient.business.user.service;
;

import com.abs.proxyclient.business.user.model.AddressDto;
import com.abs.proxyclient.business.user.model.response.AddressUserServiceCollectionDtoResponse;
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

@FeignClient(name = "USER-SERVICE", contextId = "addressClientService", path = "/user-service/api/address", dismiss404 = true)
public interface AddressClientService {

    @GetMapping
    ResponseEntity<AddressUserServiceCollectionDtoResponse> findAll();

    @GetMapping("/{addressId}")
    ResponseEntity<AddressDto> findById(
            @PathVariable("addressId")
            @NotBlank(message = "*Input must not blank!**")
            @Valid final String addressId);

    @PostMapping
    ResponseEntity<AddressDto> save(
            @RequestBody
            @NotNull(message = "*Input must not NULL!**")
            @Valid final AddressDto addressDto);

    @PutMapping
    ResponseEntity<AddressDto> update(
            @RequestBody
            @NotNull(message = "*Input must not NULL!**")
            @Valid final AddressDto addressDto);

    @PutMapping("/{addressId}")
    ResponseEntity<AddressDto> update(
            @PathVariable("addressId")
            @NotBlank(message = "*Input must not blank!**") final String addressId,
            @RequestBody
            @NotNull(message = "*Input must not NULL!**")
            @Valid final AddressDto addressDto);

    @DeleteMapping("/{addressId}")
    ResponseEntity<Boolean> deleteById(@PathVariable("addressId") @NotBlank(message = "*Input must not blank!**") @Valid final String addressId);

}