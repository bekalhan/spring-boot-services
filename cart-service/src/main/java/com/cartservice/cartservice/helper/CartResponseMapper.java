package com.cartservice.cartservice.helper;

import com.cartservice.cartservice.entity.Cart;
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
                .build();
    }
    public static CartResponse mapCartToCartResponse(Cart cart) {
        return CartResponse.builder()
                .cartId(cart.getCartId())
                .quantity(cart.getQuantity())
                .totalPrice(cart.getTotalPrice())
                .createdAt(cart.getCreatedAt())
                .build();
    }

    public static Cart mapCartResponseToCart(CartResponse cartResponse) {
        return Cart.builder()
                .cartId(cartResponse.getCartId())
                .productId(cartResponse.getProduct().getProductId())
                .userId(cartResponse.getProduct().getProductId())
                .quantity(cartResponse.getQuantity())
                .totalPrice(cartResponse.getTotalPrice())
                .createdAt(cartResponse.getCreatedAt())
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
}
