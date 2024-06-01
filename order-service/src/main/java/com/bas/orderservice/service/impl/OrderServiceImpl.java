package com.bas.orderservice.service.impl;

import com.bas.orderservice.entity.Order;
import com.bas.orderservice.entity.OrderItem;
import com.bas.orderservice.entity.Status;
import com.bas.orderservice.exception.OrderNotExist;
import com.bas.orderservice.helper.OrderMappingHelper;
import com.bas.orderservice.repository.OrderItemRepository;
import com.bas.orderservice.repository.OrderRepository;
import com.bas.orderservice.request.CartRequest;
import com.bas.orderservice.request.OrderItemRequest;
import com.bas.orderservice.request.OrderRequest;
import com.bas.orderservice.response.*;
import com.bas.orderservice.service.OrderItemService;
import com.bas.orderservice.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {


    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderItemService orderItemService;
    private final RestTemplate restTemplate;


    @Override
    public List<OrderResponse> findAll() {
        List<OrderResponse> orders = this.orderRepository.findAll().stream().map(OrderMappingHelper::orderToOrderResponse).collect(Collectors.toList());
        for(OrderResponse order: orders){
            UserResponse userResponse
                    = restTemplate.getForObject(
                    "http://AUTH-SERVICE/auth/getUserById/" + Math.toIntExact(order.getUserId().intValue()),
                    UserResponse.class);
            order.setUserResponse(userResponse);
        }
        return  orders;
    }

    @Override
    public OrderResponse findById(Long orderId) {


        return this.orderRepository.findById(orderId).map(OrderMappingHelper::orderToOrderResponse).orElseThrow(()-> new OrderNotExist("Order not found"));
    }

    @Override
    public List<OrderResponse> findByUserId(Long userId) {
        List<Order> orders = this.orderRepository.findByUserId(userId).orElseThrow(() -> new OrderNotExist("Order not found"));
        UserResponse userResponse
                = restTemplate.getForObject(
                "http://AUTH-SERVICE/auth/getUserById/" + Math.toIntExact(userId),
                UserResponse.class);
        List<OrderResponse> orderResponses = new ArrayList<>();
        for(Order order: orders){

            OrderResponse orderResponse = OrderResponse.builder().build();
            PaymentResponse paymentResponse
                    = restTemplate.getForObject(
                    "http://PAYMENT-SERVICE/payment/getPayment/" +order.getPaymentId(),
                    PaymentResponse.class);

            List<OrderItemResponse> orderItems = this.orderItemService.getOrderItemsByOrderId(order.getId());
            orderResponse.setPaymentResponse(paymentResponse);
            orderResponse.setOrderId(order.getId());
            orderResponse.setOrderStatus(order.getStatus());
            orderResponse.setCreatedAt(order.getCreatedAt());
            orderResponse.setTotalPrice(order.getTotalAmount());
            orderResponse.setTotalQuantity(order.getTotalQuantity());
            orderResponse.setOrderItemResponse(orderItems);
            orderResponses.add(orderResponse);
            orderResponse.setUserResponse(userResponse);
        }

        return orderResponses;

    }

    @Override
    public Long create(OrderRequest orderRequest, CartRequest cartRequest) {
        System.out.println("cartrequest" + cartRequest);
        System.out.println("orderrequest" + orderRequest);
        Order order = Order.builder()
                .userId(orderRequest.getUserId())
                .totalAmount(cartRequest.getCartPrice())
                .status(orderRequest.getStatus())
                .totalQuantity(cartRequest.getTotalQuantity())
                .cartId(orderRequest.getCartId())
                .createdAt(LocalDateTime.now())
                .paymentId(orderRequest.getPaymentId())
                .build();
        Order savedOrder = this.orderRepository.save(order);
        CartResponse cartResponse
                = restTemplate.getForObject(
                "http://CART-SERVICE/cart/getCartByUserId/" + cartRequest.getUserId(),
                CartResponse.class);
        List<CartItemResponse> cartItemResponses = cartResponse.getCartItems();
        for (CartItemResponse cartItem : cartResponse.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(savedOrder);
            orderItem.setProductId(cartItem.getProduct().getProductId());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setUnitPrice(cartItem.getTotalPrice());
            orderItemRepository.save(orderItem);
        }
        return order.getId();
    }

    @Override
    public OrderResponse update(Long orderId, OrderRequest orderDto) {
        return null;
    }

    @Override
    public void deleteById(Long orderId) {

    }

    public void changeStatus (Long orderId , OrderRequest orderRequest) {
        Order order = this.orderRepository.findById(orderId).orElseThrow(()-> new OrderNotExist("Order not found"));
        order.setStatus(orderRequest.getStatus());
        this.orderRepository.save(order);
    }
}
