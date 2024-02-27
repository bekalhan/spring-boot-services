package com.cartservice.cartservice.service.impl;

import com.cartservice.cartservice.dto.CartDTO;
import com.cartservice.cartservice.entity.Cart;
import com.cartservice.cartservice.repository.CartRepository;
import com.cartservice.cartservice.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepo;


    public Long addToCart(CartDTO cartDTO) {
        Cart cart=Cart.builder().productName(cartDTO.getProductName()).build();
        cartRepo.save(cart);
        return cart.getCartId();
    }

    public String deleteFromCart(Long cartId) {
        cartRepo.deleteById(cartId);
        return "Product deleted from cart";
    }

    public List<Cart> viewCart(List<Long>cartIds) {
        return cartIds.stream().map(val->cartRepo.findById(val).get()).collect(Collectors.toList());
    }
}