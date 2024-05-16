package com.bas.orderservice.helper;

import com.bas.orderservice.entity.Order;
import com.bas.orderservice.request.OrderRequest;
import com.bas.orderservice.response.OrderResponse;

public class OrderMappingHelper {
    public static OrderResponse map(final Order order) {
        return null;
        /*OrderDTO.builder()
                .orderId(order.getOrderId())
                .orderDate(order.getOrderDate())
                .orderDesc(order.getOrderDesc())
                .orderPrice(order.getOrderPrice())
                .cartDto(
                        CartDto.builder()
                                .cartId(order.getCart().getId())
                                .build())
                .build();*/
    }

    public static Order map(final OrderRequest orderRequest) {
        return null; /*Order.builder()
                .orderId(orderDto.getOrderId())
                .orderDate(orderDto.getOrderDate())
                .orderDesc(orderDto.getOrderDesc())
                .orderPrice(orderDto.getOrderPrice())
                .cart(
                        Cart.builder()
                                .Id(orderDto.getCartDto().getCartId())
                                .build())
                .build();*/
    }
}
