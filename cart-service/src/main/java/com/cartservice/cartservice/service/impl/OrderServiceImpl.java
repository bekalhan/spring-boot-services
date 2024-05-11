package com.cartservice.cartservice.service.impl;

import com.cartservice.cartservice.entity.Order;
import com.cartservice.cartservice.entity.Status;
import com.cartservice.cartservice.exception.OrderNotExist;
import com.cartservice.cartservice.helper.OrderMapperHelper;
import com.cartservice.cartservice.repository.CartRepository;
import com.cartservice.cartservice.repository.OrderRepository;
import com.cartservice.cartservice.request.CartRequest;
import com.cartservice.cartservice.request.OrderRequest;
import com.cartservice.cartservice.response.BasicOrderResponse;
import com.cartservice.cartservice.response.CartResponse;
import com.cartservice.cartservice.response.OrderResponse;
import com.cartservice.cartservice.service.CartService;
import com.cartservice.cartservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final RestTemplate restTemplate;
    @Override
    public List<OrderResponse> getAllOrders() {
       List<Order> orders = orderRepository.findAll();
       List<OrderResponse> orderResponses = new ArrayList<>();
       for (Order order: orders){
           List<CartResponse> cartResponses = cartService.getCartsByUserId(order.getUserId());
           OrderResponse orderResponse = OrderResponse.builder()
                   .orderId(order.getOrderId())
                   .carts(cartResponses)
                   .totalQuantity(cartResponses.stream().mapToInt(CartResponse::getQuantity).sum())
                   .orderPrice(cartResponses.stream().mapToDouble(cartResponse -> cartResponse.getProduct().getPrice()*cartResponse.getQuantity()).sum())
                   .status(order.getStatus())
                   .createdAt(order.getCreatedAt())
                   .build();
           orderResponses.add(orderResponse);
       }
        return orderResponses;
    }



    @Override
    public OrderResponse getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(()-> new OrderNotExist("Order does not exist"));
        List<CartResponse> cartResponses = cartService.getCartsByUserId(order.getUserId());
        OrderResponse orderResponse = OrderResponse.builder()
                .orderId(order.getOrderId())
                .carts(cartResponses)
                .totalQuantity(cartResponses.stream().mapToInt(CartResponse::getQuantity).sum())
                .orderPrice(cartResponses.stream().mapToDouble(cartResponse -> cartResponse.getProduct().getPrice()*cartResponse.getQuantity()).sum())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .build();
        return orderResponse;
    }

    @Override
    public Long createOrder(OrderRequest orderRequest) {

        Order order = Order.builder()
                .userId(orderRequest.getUserId())
                .status(orderRequest.getStatus())
                .createdAt(LocalDateTime.now())
                .build();

        return  orderRepository.save(order).getOrderId();

    }

    @Override
    public BasicOrderResponse updateOrder(Long orderId, OrderRequest orderRequest) {
        Order order = orderRepository.findById(orderId).orElseThrow(()-> new OrderNotExist("Order does not exist"));
        order.setStatus(orderRequest.getStatus());
        order.setUserId(orderRequest.getUserId());
        orderRepository.save(order);
        return OrderMapperHelper.mapOrderToBasicOrderResponse(order);
    }

    @Override
    public String deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
        return orderId+" numaralı order silindi.";
    }
}
