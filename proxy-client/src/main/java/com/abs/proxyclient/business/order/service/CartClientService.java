package com.abs.proxyclient.business.order.service;

import com.abs.proxyclient.business.order.model.CartDto;
import com.abs.proxyclient.business.order.model.response.CartOrderServiceDtoCollectionResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "ORDER-SERVICE", contextId = "cartClientService", path = "/order-service/api/carts")
public interface CartClientService {
    @GetMapping
    public ResponseEntity<CartOrderServiceDtoCollectionResponse> findAll();

    @GetMapping("/{cartId}")
    public ResponseEntity<CartDto> findById(
            @PathVariable("cartId")
            @NotBlank(message = "Input must not be blank!")
            @Valid final String cartId);

    @PostMapping
    public ResponseEntity<CartDto> save(
            @RequestBody
            @NotNull(message = "Input must not be NULL!")
            @Valid final CartDto cartDto);

    @PutMapping
    public ResponseEntity<CartDto> update(
            @RequestBody
            @NotNull(message = "Input must not be NULL!")
            @Valid final CartDto cartDto);

    @PutMapping("/{cartId}")
    public ResponseEntity<CartDto> update(
            @PathVariable("cartId")
            @NotBlank(message = "Input must not be blank!")
            @Valid final String cartId,
            @RequestBody
            @NotNull(message = "Input must not be NULL!")
            @Valid final CartDto cartDto);

    @DeleteMapping("/{cartId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("cartId") final String cart);
}
