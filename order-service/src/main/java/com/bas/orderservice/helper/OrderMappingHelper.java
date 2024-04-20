package com.bas.orderservice.helper;

import com.bas.orderservice.dto.CartDto;
import com.bas.orderservice.dto.OrderDTO;
import com.bas.orderservice.entity.Cart;
import com.bas.orderservice.entity.Order;

public class OrderMappingHelper {
    public static OrderDTO map(final Order order) {
        return OrderDTO.builder()
                .orderId(order.getOrderId())
                .orderDate(order.getOrderDate())
                .orderDesc(order.getOrderDesc())
                .orderFee(order.getOrderFee())
                .cartDto(
                        CartDto.builder()
                                .cartId(order.getCart().getCartId())
                                .build())
                .build();
    }

    public static Order map(final OrderDTO orderDto) {
        return Order.builder()
                .orderId(orderDto.getOrderId())
                .orderDate(orderDto.getOrderDate())
                .orderDesc(orderDto.getOrderDesc())
                .orderFee(orderDto.getOrderFee())
                .cart(
                        Cart.builder()
                                .cartId(orderDto.getCartDto().getCartId())
                                .build())
                .build();
    }
}
