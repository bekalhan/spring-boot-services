package com.bas.orderservice.helper;

import com.bas.orderservice.dto.CartDto;
import com.bas.orderservice.dto.OrderDTO;
import com.bas.orderservice.dto.UserDto;
import com.bas.orderservice.entity.Cart;
import com.bas.orderservice.entity.Order;

import java.util.stream.Collectors;

public class CartMappingHelper {
    public static CartDto toCartDto(final Cart cart) {
        if(cart == null) return null;
    return CartDto.builder()
            .cartId(cart.getId())
            .userId(cart.getUserId())
            .orderDtos(cart.getOrders().stream()
                    .map(order -> OrderDTO.builder()
                            .orderId(order.getOrderId())
                            .orderDate(order.getOrderDate())
                            .orderDesc(order.getOrderDesc())
                            .orderPrice(order.getOrderPrice())
                            .status(order.getStatus())
                            .build())
                    .collect(Collectors.toSet()))
            .build();
}

    public static Cart toCart(final CartDto cartDto) {
        if(cartDto == null) return null;
        return Cart.builder()
                .Id(cartDto.getCartId())
                .userId(cartDto.getUserId())
                .orders(cartDto.getOrderDtos()
                        .stream()
                        .map(orderDto -> Order.builder()
                                .orderId(orderDto.getOrderId())
                                .orderDate(orderDto.getOrderDate())
                                .orderDesc(orderDto.getOrderDesc())
                                .orderPrice(orderDto.getOrderPrice())
                                .build())
                        .collect(Collectors.toSet()))
                .build();
    }

}
