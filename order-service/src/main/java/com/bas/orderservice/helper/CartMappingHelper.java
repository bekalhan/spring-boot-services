package com.bas.orderservice.helper;

import com.bas.orderservice.dto.CartDto;
import com.bas.orderservice.dto.UserDto;
import com.bas.orderservice.entity.Cart;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public interface CartMappingHelper {

    static CartDto map(Cart cart) {
        return CartDto.builder()
                .cartId(cart.getCartId())
                .userId(cart.getUserId())
                .userDto(UserDto.builder().userId(cart.getUserId()).build())
                .build();
    }
    static Cart map(final CartDto cartDto) {
        return Cart.builder()
                .cartId(cartDto.getCartId())
                .userId(cartDto.getUserId())
                .build();
    }
}
