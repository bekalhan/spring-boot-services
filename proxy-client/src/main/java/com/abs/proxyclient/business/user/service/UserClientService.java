package com.abs.proxyclient.business.user.service;

import com.abs.proxyclient.business.user.model.UserDto;
import com.abs.proxyclient.business.user.model.response.UserUserServiceCollectionDtoResponse;
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

@FeignClient(name = "USER-SERVICE", contextId = "userClientService", path = "/user-service/api/users", dismiss404 = true)
public interface UserClientService {

    @GetMapping
    ResponseEntity<UserUserServiceCollectionDtoResponse> findAll();

    @GetMapping("/{userId}")
    ResponseEntity<UserDto> findById(
            @PathVariable("userId")
            @NotBlank(message = "*Input must not blank!**")
            @Valid final String userId);

    @GetMapping("/username/{username}")
    ResponseEntity<UserDto> findByUsername(
            @PathVariable("username")
            @NotBlank(message = "*Input must not blank!**")
            @Valid final String username);

    @PostMapping
    ResponseEntity<UserDto> save(
            @RequestBody
            @NotNull(message = "*Input must not NULL!**")
            @Valid final UserDto userDto);

    @PutMapping
    ResponseEntity<UserDto> update(
            @RequestBody
            @NotNull(message = "*Input must not NULL!**")
            @Valid final UserDto userDto);

    @PutMapping("/{userId}")
    ResponseEntity<UserDto> update(
            @PathVariable("userId")
            @NotBlank(message = "*Input must not blank!**") final String userId,
            @RequestBody
            @NotNull(message = "*Input must not NULL!**")
            @Valid final UserDto userDto);

    @DeleteMapping("/{userId}")
    ResponseEntity<Boolean> deleteById(@PathVariable("userId") @NotBlank(message = "*Input must not blank!**") @Valid final String userId);

}