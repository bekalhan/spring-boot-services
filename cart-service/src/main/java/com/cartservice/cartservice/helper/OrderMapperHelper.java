package com.cartservice.cartservice.helper;

import com.cartservice.cartservice.entity.Cart;
import com.cartservice.cartservice.entity.Order;
import com.cartservice.cartservice.response.BasicCartResponse;
import com.cartservice.cartservice.response.BasicOrderResponse;
import com.cartservice.cartservice.response.CartResponse;
import com.cartservice.cartservice.response.OrderResponse;

public class OrderMapperHelper {
    public static BasicOrderResponse mapOrderToBasicOrderResponse(Order order) {
    return BasicOrderResponse.builder()
            .orderId(order.getOrderId())
            .userId(order.getUserId())
            .status(order.getStatus())
            .createdAt(order.getCreatedAt())
            .build();

    }



}
