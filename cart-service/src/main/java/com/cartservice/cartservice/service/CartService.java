package com.cartservice.cartservice.service;

import com.cartservice.cartservice.dto.CartDTO;
import com.cartservice.cartservice.entity.Cart;

import java.util.List;

public interface CartService {
     Long addToCart(CartDTO cartDTO);
    String deleteFromCart(Long cartId);
    List<Cart> viewCart(List<Long>cartIds);
}
