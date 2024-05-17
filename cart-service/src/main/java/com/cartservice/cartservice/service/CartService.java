package com.cartservice.cartservice.service;

import com.cartservice.cartservice.request.CartRequest;
import com.cartservice.cartservice.response.BasicCartResponse;
import com.cartservice.cartservice.response.CartResponse;

import java.util.List;
import java.util.Optional;

public interface CartService {
    List<CartResponse> getAllCarts();

    CartResponse getCartById(Long cartId);
    Long createCart(CartRequest cartRequest);
    BasicCartResponse updateCart(Long cartId, CartRequest cartRequest);
    String deleteCart(Long cartId);
    Optional<CartResponse> getCartByUserId (Long userId);
}
