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
                .orderPrice(order.getOrderPrice())
                .cartDto(
                        CartDto.builder()
                                .cartId(order.getCart().getId())
                                .build())
                .build();
    }

    public static Order map(final OrderDTO orderDto) {
        return Order.builder()
                .orderId(orderDto.getOrderId())
                .orderDate(orderDto.getOrderDate())
                .orderDesc(orderDto.getOrderDesc())
                .orderPrice(orderDto.getOrderPrice())
                .cart(
                        Cart.builder()
                                .Id(orderDto.getCartDto().getCartId())
                                .build())
                .build();
    }
}
