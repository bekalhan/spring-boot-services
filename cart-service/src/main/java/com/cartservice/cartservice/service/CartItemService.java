package com.cartservice.cartservice.service;

import com.cartservice.cartservice.entity.CartItem;
import com.cartservice.cartservice.request.CartItemRequest;
import com.cartservice.cartservice.response.BasicCartItemResponse;
import com.cartservice.cartservice.response.CartItemResponse;

import java.util.List;

public interface CartItemService {
    Long addToCart(CartItemRequest cartItemRequest);
    String deleteFromCart(Long cartItemId);
    List<CartItem> viewCart(List<Long>cartItemIds);
    List<BasicCartItemResponse> getAllCart();
    CartItemResponse getCartById(Long cartItemId);
    List<CartItemResponse> getCartsByCartId(Long cartId);
    BasicCartItemResponse updateCart(Long cartItemId, CartItemRequest cartItemRequest);
}
