package com.cartservice.cartservice.controller;

import com.cartservice.cartservice.request.CartRequest;
import com.cartservice.cartservice.response.BasicCartResponse;
import com.cartservice.cartservice.response.CartResponse;
import com.cartservice.cartservice.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("cart/")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping
    public ResponseEntity<List<CartResponse>> getAllCarts(){
        return new ResponseEntity<>(cartService.getAllCarts(), HttpStatus.OK);
    }

    @GetMapping("getCart/{cartId}")
    public ResponseEntity<CartResponse> getCartById(@PathVariable("cartId") Long cartId){
        return new ResponseEntity<>(cartService.getCartById(cartId),HttpStatus.OK);
    }

    @GetMapping("getCartByUserId/{userId}")
    public ResponseEntity<Optional<CartResponse>> getCartByUserId(@PathVariable("userId") Long userId){
        return new ResponseEntity<>(cartService.getCartByUserId(userId),HttpStatus.OK);
    }

    @PostMapping("createCart")
    public ResponseEntity<Long> createCart(@RequestBody@Valid CartRequest cartRequest){
        return new ResponseEntity<>(cartService.createCart(cartRequest),HttpStatus.CREATED);
    }

    @PutMapping("updateCart/{cartId}")
    public ResponseEntity<BasicCartResponse> updateCart(@PathVariable("cartId") Long cartId, @RequestBody@Valid CartRequest cartRequest){
        return new ResponseEntity<>(cartService.updateCart(cartId,cartRequest),HttpStatus.OK);
    }

    @DeleteMapping("deleteCart/{cartId}")
    public ResponseEntity<String> deleteCart(@PathVariable("cartId") Long cartId){
        return new ResponseEntity<>(cartService.deleteCart(cartId),HttpStatus.OK);
    }
}
