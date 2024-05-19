package com.bas.orderservice.service.impl;

import com.bas.orderservice.entity.OrderItem;
import com.bas.orderservice.helper.OrderItemMappingHelper;
import com.bas.orderservice.repository.OrderItemRepository;
import com.bas.orderservice.request.CartRequest;
import com.bas.orderservice.request.OrderItemRequest;
import com.bas.orderservice.response.CartItemResponse;
import com.bas.orderservice.response.CartResponse;
import com.bas.orderservice.response.OrderItemResponse;
import com.bas.orderservice.response.ProductResponse;
import com.bas.orderservice.service.OrderItemService;
import com.bas.orderservice.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;

    private final RestTemplate restTemplate;
    @Override
    public List<OrderItemResponse> getAllOrderItems() {
        return this.orderItemRepository.findAll().stream().map(OrderItemMappingHelper::orderItemToOrderItemResponse).collect(Collectors.toList());
    }

    @Override
    public OrderItemResponse getOrderItemById(Long orderItemId) {
        return this.orderItemRepository.findById(orderItemId).map(OrderItemMappingHelper::orderItemToOrderItemResponse).orElse(null);
    }

    @Override
    public List<OrderItemResponse> getOrderItemsByOrderId(Long orderId) {
        List<OrderItem> orderItem = this.orderItemRepository.findOrderItemsByOrderId(orderId).orElseThrow(()-> new RuntimeException("Order not found"));
        List<OrderItemResponse> orderItemResponses = new ArrayList<>();

        for(OrderItem item: orderItem){
            ProductResponse productResponse
                    = restTemplate.getForObject(
                    "http://PRODUCT-SERVICE/product/action/product/" + item.getProductId(),
                    ProductResponse.class);
            OrderItemResponse orderItemResponse = OrderItemResponse.builder().build();
            orderItemResponse.setProduct(productResponse);
            orderItemResponse.setQuantity(item.getQuantity());
            orderItemResponse.setTotalPrice(item.getUnitPrice());
            orderItemResponses.add(orderItemResponse);
        }

        return orderItemResponses;

    }

    @Override
    public OrderItemResponse updateOrderItem(Long orderItemId, OrderItemRequest orderItemRequest) {
        return null;
    }

    @Override
    public String deleteFromOrderItem(Long orderItemId) {
        return null;
    }
}
