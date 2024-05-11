package com.cartservice.cartservice.service;

import com.cartservice.cartservice.request.CartRequest;
import com.cartservice.cartservice.request.OrderRequest;
import com.cartservice.cartservice.response.BasicCartResponse;
import com.cartservice.cartservice.response.BasicOrderResponse;
import com.cartservice.cartservice.response.CartResponse;
import com.cartservice.cartservice.response.OrderResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OrderService {
    List<OrderResponse> getAllOrders();

    OrderResponse getOrderById(Long orderId);
    Long createOrder(OrderRequest orderRequest);
    BasicOrderResponse updateOrder(Long orderId, OrderRequest orderRequest);
    String deleteOrder(Long orderId);
}
