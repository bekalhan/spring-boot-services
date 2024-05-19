package com.bas.orderservice.service;


import com.bas.orderservice.entity.Order;
import com.bas.orderservice.request.CartRequest;
import com.bas.orderservice.request.OrderRequest;
import com.bas.orderservice.response.OrderResponse;

import java.util.List;


public interface OrderService {


    List<OrderResponse> findAll();
    OrderResponse findById(final Long orderId);
    List<OrderResponse> findByUserId(final Long userId);
    Long create(final OrderRequest orderRequest, final CartRequest cartRequest);
    OrderResponse update(final Long orderId, final OrderRequest orderDto);
    void deleteById(final Long orderId);
}
