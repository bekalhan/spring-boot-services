package com.bas.orderservice.helper;

import com.bas.orderservice.entity.Order;
import com.bas.orderservice.request.OrderRequest;
import com.bas.orderservice.response.OrderResponse;

public class OrderMappingHelper {
    public static OrderResponse orderToOrderResponse(final Order order) {
        return OrderResponse.builder()
                .orderId(order.getId())
                .orderStatus(order.getStatus())
                .createdAt(order.getCreatedAt())
                .totalPrice(order.getTotalAmount())
                .userId(order.getUserId())
                .build();


    }


}
