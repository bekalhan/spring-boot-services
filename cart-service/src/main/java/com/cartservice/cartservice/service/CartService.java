package com.cartservice.cartservice.service;

import com.cartservice.cartservice.dto.CartDTO;
import com.cartservice.cartservice.entity.Cart;
import com.cartservice.cartservice.request.CartRequest;
import com.cartservice.cartservice.response.BasicCartResponse;
import com.cartservice.cartservice.response.CartResponse;

import java.util.List;
import java.util.Optional;

public interface CartService {
     Long addToCart(CartRequest cartRequest);
    String deleteFromCart(Long cartId);
    List<Cart> viewCart(List<Long>cartIds);
    List<BasicCartResponse> getAllCart();
    CartResponse getCartById(Long cartId);
    List<CartResponse> getCartsByUserId(Long userId);
    BasicCartResponse updateCart(Long cartId, CartRequest cartRequest);
}
