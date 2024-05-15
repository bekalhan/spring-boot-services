package com.cartservice.cartservice.helper;

import com.cartservice.cartservice.entity.CartItem;
import com.cartservice.cartservice.request.CartItemRequest;
import com.cartservice.cartservice.response.BasicCartItemResponse;
import com.cartservice.cartservice.response.CartItemResponse;
import com.cartservice.cartservice.response.ProductResponse;
import com.cartservice.cartservice.response.UserResponse;

public class CartItemResponseMapper {
    public static BasicCartItemResponse mapCartItemToCartItemResponseBasic(CartItem cartItem) {
        return BasicCartItemResponse.builder()
                .cartItemId(cartItem.getCartItemId())
                .cartId(cartItem.getCart().getCartId())
                .quantity(cartItem.getQuantity())
                .totalPrice(cartItem.getTotalPrice())
                .createdAt(cartItem.getCreatedAt())
                .status(cartItem.getStatus())
                .build();
    }
    public static CartItemResponse mapCartItemToCartItemResponse(CartItem cartItem) {
        return CartItemResponse.builder()
                .cartItemId(cartItem.getCartItemId())
                .cartId(cartItem.getCart().getCartId())
                .quantity(cartItem.getQuantity())
                .totalPrice(cartItem.getTotalPrice())
                .createdAt(cartItem.getCreatedAt())
                .status(cartItem.getStatus())
                .build();
    }

    public static CartItem mapCartItemRequestToCart(CartItemRequest cartItemRequest) {
        return CartItem.builder()
                .productId(cartItemRequest.getProductId())
                .quantity(cartItemRequest.getQuantity())
                .status(cartItemRequest.getStatus())
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

    public static CartItemResponse buildCartItemResponse(CartItem cartItem) {
        return CartItemResponse.builder()
                .quantity(cartItem.getQuantity())
                .status(cartItem.getStatus())
                .totalPrice(cartItem.getTotalPrice())
                .createdAt(cartItem.getCreatedAt()).build();

    }
}
