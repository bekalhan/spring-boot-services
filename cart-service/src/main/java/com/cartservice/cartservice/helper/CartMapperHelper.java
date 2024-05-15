package com.cartservice.cartservice.helper;

import com.cartservice.cartservice.entity.Cart;
import com.cartservice.cartservice.response.BasicCartResponse;

public class CartMapperHelper {
    public static BasicCartResponse mapCartToBasicCartResponse(Cart cart) {
    return BasicCartResponse.builder()
            .cartId(cart.getCartId())
            .userId(cart.getUserId())
            .status(cart.getStatus())
            .createdAt(cart.getCreatedAt())
            .build();

    }



}
