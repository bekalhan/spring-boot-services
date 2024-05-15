package com.cartservice.cartservice.service;

import com.cartservice.cartservice.request.CartRequest;
import com.cartservice.cartservice.response.BasicCartResponse;
import com.cartservice.cartservice.response.CartResponse;

import java.util.List;

public interface CartService {
    List<CartResponse> getAllCarts();

    CartResponse getCartById(Long cartId);
    Long createCart(CartRequest cartRequest);
    BasicCartResponse updateCart(Long cartId, CartRequest cartRequest);
    String deleteCart(Long cartId);
}
