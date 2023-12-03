package com.bas.orderservice.controller;

import com.bas.orderservice.constant.PageableConstants;
import com.bas.orderservice.dto.CartDto;
import com.bas.orderservice.dto.reponse.DtoCollectionResponse;
import com.bas.orderservice.repository.CartRepository;
import com.bas.orderservice.service.CartService;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping
    public ResponseEntity<DtoCollectionResponse<CartDto>> findAll() {
        return ResponseEntity.ok(new DtoCollectionResponse<>(this.cartService.findAll()));
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartDto> findById(
                                            @PathVariable("cartId")
                                            @Validated final Integer cartId)
    {
        try {
            return ResponseEntity.ok(this.cartService.findById(cartId));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }
    @PostMapping
    public ResponseEntity<CartDto> save(@RequestBody @Validated final CartDto cartDto) {
        return ResponseEntity.ok(this.cartService.save(cartDto));
    }

    @PutMapping
    public ResponseEntity<CartDto> update(
                                          @RequestBody
                                          @Validated final CartDto cartDto) {

        return ResponseEntity.ok(this.cartService.update(cartDto));
    }

    @PutMapping("/{cartId}")
    public ResponseEntity<CartDto> update(
                                          @PathVariable("cartId")

                                          @Validated final Integer cartId,
                                          @RequestBody

                                          @Validated final CartDto cartDto) {
        return ResponseEntity.ok(this.cartService.update(cartId, cartDto));
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<Boolean> deleteById(
                                              @PathVariable("cartId") final Integer cartId) {

        this.cartService.deleteById(cartId);
        return ResponseEntity.ok(true);
    }
}
