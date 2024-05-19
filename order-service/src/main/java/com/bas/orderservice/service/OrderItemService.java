package com.bas.orderservice.service;

import com.bas.orderservice.entity.OrderItem;
import com.bas.orderservice.request.CartRequest;
import com.bas.orderservice.request.OrderItemRequest;
import com.bas.orderservice.response.OrderItemResponse;

import java.util.List;

public interface OrderItemService {

    List<OrderItemResponse> getAllOrderItems();
    OrderItemResponse getOrderItemById(Long orderItemId);
    List<OrderItemResponse> getOrderItemsByOrderId(Long orderId);


    OrderItemResponse updateOrderItem(Long orderItemId, OrderItemRequest orderItemRequest);
    String deleteFromOrderItem(Long orderItemId);
}
