package com.bas.orderservice.service;


import com.bas.orderservice.entity.Order;
import com.bas.orderservice.request.OrderRequest;
import com.bas.orderservice.response.OrderResponse;

import java.util.List;


public interface OrderService {
   Long bookOrder(OrderRequest orderRequest);

   String cancelOrder(Long orderId);

   List<Order> viewOrders(List<Long> orderIds);

    List<Order> viewOrderByStatus(List<Long> orderIds, String status);

    List<OrderResponse> findAll();
    OrderResponse findById(final Long orderId);
    OrderRequest save(final OrderRequest orderRequest);
    OrderResponse update(final OrderRequest orderRequest);
    OrderResponse update(final Long orderId, final OrderRequest orderDto);
    void deleteById(final Long orderId);
}
