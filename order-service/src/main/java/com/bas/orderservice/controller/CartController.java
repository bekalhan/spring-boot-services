package com.bas.orderservice.controller;

import com.bas.orderservice.dto.CartDto;
import com.bas.orderservice.service.CartService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {
    private CartService cartService;

    //findall
    @GetMapping
    public ResponseEntity<List<CartDto>> findAll(){

        return ResponseEntity.ok(this.cartService.findAll());
        //var allCarts = cartService.findall();
        //return ResponseEntity.status(HttpStatus.OK).body(allCarts);
    }

    //findById
    @GetMapping("/{cartId}")
    public ResponseEntity<CartDto> findById(
            @PathVariable("cartId")
            @NotBlank(message = "Input must not be blank")
            @Valid final Long cartId) {
        return ResponseEntity.ok(this.cartService.findById(cartId));
    }
    //save
    @PostMapping
    public ResponseEntity<CartDto> save(
            @RequestBody
            @NotNull(message = "Input must not be NULL!")
            @Valid final CartDto cartDto) {
        return ResponseEntity.ok(this.cartService.save(cartDto));
    }
    //update
    @PutMapping
    public ResponseEntity<CartDto> update(
            @RequestBody
            @NotNull(message = "Input must not be NULL")
            @Valid final CartDto cartDto) {
        return ResponseEntity.ok(this.cartService.update(cartDto));
    }
    //updatebyId
    @PutMapping("/{cartId}")
    public ResponseEntity<CartDto> update(
            @PathVariable("cartId")
            @NotBlank(message = "Input must not be blank")
            @Valid final Long cartId,
            @RequestBody
            @NotNull(message = "Input must not be NULL")
            @Valid final CartDto cartDto) {

        return ResponseEntity.ok(this.cartService.update(cartId, cartDto));
    }
    //deleteById
    @DeleteMapping("/{cartId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("cartId") final Long cartId) {
        this.cartService.deleteById(cartId);
        return ResponseEntity.ok(true);
    }
}
