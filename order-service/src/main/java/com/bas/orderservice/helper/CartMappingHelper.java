package com.bas.orderservice.helper;

import com.bas.orderservice.dto.CartDto;
import com.bas.orderservice.dto.UserDto;
import com.bas.orderservice.entity.Cart;

public class CartMappingHelper {
    public static CartDto map(final Cart cart) {
    return CartDto.builder()
            .cartId(cart.getCartId())
            .userId(cart.getUserId())
            .userDto(
                    UserDto.builder()
                            .userId(cart.getUserId())
                            .build())
            .build();
}

    public static Cart map(final CartDto cartDto) {
        return Cart.builder()
                .cartId(cartDto.getCartId())
                .userId(cartDto.getUserId())
                .build();
    }

}
