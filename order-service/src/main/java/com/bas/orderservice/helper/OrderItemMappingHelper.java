package com.bas.orderservice.helper;

import com.bas.orderservice.entity.Order;
import com.bas.orderservice.entity.OrderItem;
import com.bas.orderservice.request.OrderItemRequest;
import com.bas.orderservice.response.OrderItemResponse;
import com.bas.orderservice.response.OrderResponse;

public class OrderItemMappingHelper {
    public static OrderItemResponse orderItemToOrderItemResponse(final OrderItem orderItem) {
        return OrderItemResponse.builder()
                .orderItemId(orderItem.getOrderItemId())
                .orderId(orderItem.getOrder().getId())
                .build();


    }



}
