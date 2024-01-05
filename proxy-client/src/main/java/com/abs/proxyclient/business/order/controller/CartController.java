package com.abs.proxyclient.business.order.controller;

import com.abs.proxyclient.business.order.model.CartDto;
import com.abs.proxyclient.business.order.model.response.CartOrderServiceDtoCollectionResponse;
import com.abs.proxyclient.business.order.service.CartClientService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartClientService cartClientService;


    @GetMapping
    public ResponseEntity<CartOrderServiceDtoCollectionResponse> findAll() {
        return ResponseEntity.ok(this.cartClientService.findAll().getBody());
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartDto> findById(
            @PathVariable("cartId")
            @NotBlank(message = "Input must not be blank!")
            @Valid final String cartId) {
        return ResponseEntity.ok(this.cartClientService.findById(cartId).getBody());
    }

    @PostMapping
    public ResponseEntity<CartDto> save(
            @RequestBody
            @NotNull(message = "Input must not be NULL!")
            @Valid final CartDto cartDto) {
        return ResponseEntity.ok(this.cartClientService.save(cartDto).getBody());
    }

    @PutMapping
    public ResponseEntity<CartDto> update(
            @RequestBody
            @NotNull(message = "Input must not be NULL!")
            @Valid final CartDto cartDto) {
        return ResponseEntity.ok(this.cartClientService.update(cartDto).getBody());
    }

    @PutMapping("/{cartId}")
    public ResponseEntity<CartDto> update(
            @PathVariable("cartId")
            @NotBlank(message = "Input must not be blank!")
            @Valid final String cartId,
            @RequestBody
            @NotNull(message = "Input must not be NULL!")
            @Valid final CartDto cartDto) {
        return ResponseEntity.ok(this.cartClientService.update(cartId, cartDto).getBody());
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("cartId") final String cartId) {
        this.cartClientService.deleteById(cartId).getBody();
        return ResponseEntity.ok(true);
    }
}
