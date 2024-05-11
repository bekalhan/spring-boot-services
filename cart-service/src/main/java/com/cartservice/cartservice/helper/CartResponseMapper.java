package com.cartservice.cartservice.helper;

import com.cartservice.cartservice.entity.Cart;
import com.cartservice.cartservice.request.CartRequest;
import com.cartservice.cartservice.response.BasicCartResponse;
import com.cartservice.cartservice.response.CartResponse;
import com.cartservice.cartservice.response.ProductResponse;
import com.cartservice.cartservice.response.UserResponse;

public class CartResponseMapper {
    public static BasicCartResponse mapCartToCartResponseBasic(Cart cart) {
        return BasicCartResponse.builder()
                .cartId(cart.getCartId())
                .quantity(cart.getQuantity())
                .totalPrice(cart.getTotalPrice())
                .createdAt(cart.getCreatedAt())
                .status(cart.getStatus())
                .build();
    }
    public static CartResponse mapCartToCartResponse(Cart cart) {
        return CartResponse.builder()
                .cartId(cart.getCartId())
                .quantity(cart.getQuantity())
                .totalPrice(cart.getTotalPrice())
                .createdAt(cart.getCreatedAt())
                .status(cart.getStatus())
                .build();
    }

    public static Cart mapCartRequestToCart(CartRequest cartRequest) {
        return Cart.builder()
                .productId(cartRequest.getProductId())
                .userId(cartRequest.getUserId())
                .quantity(cartRequest.getQuantity())
                .totalPrice(cartRequest.getTotalPrice())
                .status(cartRequest.getStatus())
                .build();
    }

    public static ProductResponse buildProduct(ProductResponse productResponse) {
        return ProductResponse.builder()
                .productId(productResponse.getProductId())
                .name(productResponse.getName())
                .price(productResponse.getPrice())
                .imageUrl(productResponse.getImageUrl())
                .color(productResponse.getColor())
                .quantity(productResponse.getQuantity())
                .brand(productResponse.getBrand())
                .build();
    }

    public static  UserResponse buildUser(UserResponse userResponse) {
        return UserResponse.builder()
                .userId(userResponse.getUserId())
                .firstName(userResponse.getFirstName())
                .lastName(userResponse.getLastName())
                .username(userResponse.getUsername())
                .phNo(userResponse.getPhNo())
                .build();
    }

    public static  CartResponse buildCartResponse(Cart cart) {
        return CartResponse.builder()
                .quantity(cart.getQuantity())
                .status(cart.getStatus())
                .totalPrice(cart.getTotalPrice())
                .createdAt(cart.getCreatedAt()).build();

    }
}
