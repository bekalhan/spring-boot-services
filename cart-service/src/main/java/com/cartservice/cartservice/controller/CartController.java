package com.cartservice.cartservice.controller;

import com.cartservice.cartservice.dto.CartDTO;
import com.cartservice.cartservice.entity.Cart;
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

    @PostMapping("addProduct")
    public ResponseEntity<Long>addToCart(@RequestBody @Valid CartDTO cartDTO){
        System.out.println("add product");
        return new ResponseEntity<>(cartService.addToCart(cartDTO),HttpStatus.CREATED);
    }

    @DeleteMapping("deleteProduct")
    public ResponseEntity<String>deleteFromCart(@RequestParam Long cartId){
        return new ResponseEntity<>(cartService.deleteFromCart(cartId),HttpStatus.OK);
    }

    @PostMapping("allProducts")
    public ResponseEntity<List<Cart>>viewCart(@RequestBody List<Long>cartIds){
        return new ResponseEntity<>(cartService.viewCart(cartIds),HttpStatus.OK);
    }

}