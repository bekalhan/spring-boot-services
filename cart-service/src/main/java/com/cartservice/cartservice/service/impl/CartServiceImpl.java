package com.cartservice.cartservice.service.impl;

import com.cartservice.cartservice.entity.Cart;
import com.cartservice.cartservice.exception.CartNotExist;
import com.cartservice.cartservice.helper.CartMapperHelper;
import com.cartservice.cartservice.repository.CartRepository;
import com.cartservice.cartservice.request.CartRequest;
import com.cartservice.cartservice.response.BasicCartResponse;
import com.cartservice.cartservice.response.CartItemResponse;
import com.cartservice.cartservice.response.CartResponse;
import com.cartservice.cartservice.service.CartItemService;
import com.cartservice.cartservice.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartItemService cartItemService;
    private final RestTemplate restTemplate;
    @Override
    public List<CartResponse> getAllCarts() {
       List<Cart> carts = cartRepository.findAll();
       List<CartResponse> cartResponses = new ArrayList<>();
       for (Cart cart : carts){
           List<CartItemResponse> cartItemResponses = cartItemService.getCartsByCartId(cart.getCartId());
           CartResponse cartResponse = CartResponse.builder()
                   .cartId(cart.getCartId())
                   .cartItems(cartItemResponses)
                   .totalQuantity(cartItemResponses.stream().mapToInt(CartItemResponse::getQuantity).sum())
                   .cartPrice(cartItemResponses.stream().mapToDouble(cartItemResponse -> cartItemResponse.getProduct().getPrice()*cartItemResponse.getQuantity()).sum())
                   .status(cart.getStatus())
                   .createdAt(cart.getCreatedAt())
                   .build();
           cartResponses.add(cartResponse);
       }
        return cartResponses;
    }

    public Optional<CartResponse> getCartByUserId(Long userId) {
        Optional<Cart> optionalCart = cartRepository.findByUserId(userId);

        if (!optionalCart.isPresent()) {
            return Optional.empty();
        }

        Cart cart = optionalCart.get();
        List<CartItemResponse> cartItemResponses = cartItemService.getCartsByCartId(cart.getCartId());

        CartResponse cartResponse = CartResponse.builder()
                .cartId(cart.getCartId())
                .cartItems(cartItemResponses)
                .totalQuantity(cartItemResponses.stream().mapToInt(CartItemResponse::getQuantity).sum())
                .cartPrice(cartItemResponses.stream().mapToDouble(cartItemResponse -> cartItemResponse.getProduct().getPrice() * cartItemResponse.getQuantity()).sum())
                .status(cart.getStatus())
                .createdAt(cart.getCreatedAt())
                .build();

        return Optional.of(cartResponse);
    }



    @Override
    public CartResponse getCartById(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(()-> new CartNotExist("Cart does not exist"));
        List<CartItemResponse> cartItemResponse = cartItemService.getCartsByCartId(cart.getCartId());
        CartResponse cartResponse = CartResponse.builder()
                .cartId(cart.getCartId())
                .cartItems(cartItemResponse)
                .totalQuantity(cartItemResponse.stream().mapToInt(CartItemResponse::getQuantity).sum())
                .cartPrice(cartItemResponse.stream().mapToDouble(cartItemRespons -> cartItemRespons.getProduct().getPrice()*cartItemRespons.getQuantity()).sum())
                .status(cart.getStatus())
                .createdAt(cart.getCreatedAt())
                .build();
        return cartResponse;
    }

    @Override
    public Long createCart(CartRequest cartRequest) {

        Cart cart = Cart.builder()
                .userId(cartRequest.getUserId())
                .status(cartRequest.getStatus())
                .createdAt(LocalDateTime.now())
                .build();

        return  cartRepository.save(cart).getCartId();

    }

    @Override
    public BasicCartResponse updateCart(Long cartId, CartRequest cartRequest) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(()-> new CartNotExist("Cart does not exist"));
        cart.setStatus(cartRequest.getStatus());
        cart.setUserId(cartRequest.getUserId());
        cartRepository.save(cart);
        return CartMapperHelper.mapCartToBasicCartResponse(cart);
    }

    @Override
    public String deleteCart(Long cartId) {
        cartRepository.deleteById(cartId);
        return " cart with id : " + cartId + " has deleted.";
    }
}
