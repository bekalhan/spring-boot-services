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

@RestController
@RequestMapping("cart/action/")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    @GetMapping("test")
    public ResponseEntity<String>test(){
        return new ResponseEntity<>("cartController", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BasicCartResponse>>getAllCart(){
        return new ResponseEntity<>(cartService.getAllCart(),HttpStatus.OK);
    }
    @GetMapping("getCart/{cartId}")
    public ResponseEntity<CartResponse>viewCart(@PathVariable("cartId") Long cartId){
        return new ResponseEntity<>(cartService.getCartById(cartId),HttpStatus.OK);
    }

    @GetMapping("getCartByUserId/{userId}")
    public ResponseEntity<List<CartResponse>>getCartsByUserId(@PathVariable("userId") Long userId){
        return new ResponseEntity<>(cartService.getCartsByUserId(userId),HttpStatus.OK);
    }
    @PostMapping("addCart")
    public ResponseEntity<Long>addToCart(@RequestBody @Valid CartRequest cartRequest){
        System.out.println("added product");
        return new ResponseEntity<>(cartService.addToCart(cartRequest),HttpStatus.CREATED);
    }

    @PutMapping("updateCart/{cartId}")
    public ResponseEntity<BasicCartResponse>updateCart(@PathVariable("cartId") Long cartId,@RequestBody @Valid CartRequest cartRequest){
        return new ResponseEntity<>(cartService.updateCart(cartId,cartRequest),HttpStatus.OK);
    }

    @DeleteMapping("deleteCart/{cartId}")
    public ResponseEntity<String>deleteFromCart(@PathVariable("cartId") Long cartId){
        return new ResponseEntity<>(cartService.deleteFromCart(cartId),HttpStatus.OK);
    }



}