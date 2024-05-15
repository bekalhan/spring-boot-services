package com.cartservice.cartservice.controller;

import com.cartservice.cartservice.request.CartItemRequest;
import com.cartservice.cartservice.response.BasicCartItemResponse;
import com.cartservice.cartservice.response.CartItemResponse;
import com.cartservice.cartservice.service.CartItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cart/cartItem/")
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemService cartService;
    @GetMapping("test")
    public ResponseEntity<String>test(){
        return new ResponseEntity<>("cartController", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BasicCartItemResponse>>getAllCartItems(){
        return new ResponseEntity<>(cartService.getAllCart(),HttpStatus.OK);
    }
    @GetMapping("getCartItem/{cartItemId}")
    public ResponseEntity<CartItemResponse>viewCartItem(@PathVariable("cartItemId") Long cartItemId){
        return new ResponseEntity<>(cartService.getCartById(cartItemId),HttpStatus.OK);
    }

    @GetMapping("getCartItemByUserId/{cartId}")
    public ResponseEntity<List<CartItemResponse>>getCartItemsByCartId(@PathVariable("cartId") Long cartId){
        return new ResponseEntity<>(cartService.getCartsByCartId(cartId),HttpStatus.OK);
    }
    @PostMapping("addCartItem")
    public ResponseEntity<Long>addToCartItem(@RequestBody @Valid CartItemRequest cartItemRequest){
        System.out.println("added product");
        return new ResponseEntity<>(cartService.addToCart(cartItemRequest),HttpStatus.CREATED);
    }

    @PutMapping("updateCartItem/{cartItemId}")
    public ResponseEntity<BasicCartItemResponse>updateCartItem(@PathVariable("cartItemId") Long cartItemId, @RequestBody @Valid CartItemRequest cartItemRequest){
        return new ResponseEntity<>(cartService.updateCart(cartItemId, cartItemRequest),HttpStatus.OK);
    }

    @DeleteMapping("deleteCartItem/{cartItemId}")
    public ResponseEntity<String>deleteFromCartItem(@PathVariable("cartItemId") Long cartItemId){
        return new ResponseEntity<>(cartService.deleteFromCart(cartItemId),HttpStatus.OK);
    }



}