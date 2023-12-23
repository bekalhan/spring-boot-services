package com.bas.orderservice.helper;

import com.bas.orderservice.dto.CartDto;
import com.bas.orderservice.dto.OrderDto;
import com.bas.orderservice.dto.OrderResponse;
import com.bas.orderservice.entity.Cart;
import com.bas.orderservice.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public interface OrderMappingHelper {
    public static OrderDto map(final Order order) {
        return OrderDto.builder()
                .orderId(order.getOrderId())
                .orderDate(order.getOrderDate())
                .orderDesc(order.getOrderDesc())
                .totalPrice(order.getTotalPrice())
                .cartDto(
                        CartDto.builder()
                                .cartId(order.getCart().getCartId())
                                .build())
                .build();
    }

    public static Order map(final OrderDto orderDto) {
        return Order.builder()
                .orderId(orderDto.getOrderId())
                .orderDate(orderDto.getOrderDate())
                .orderDesc(orderDto.getOrderDesc())
                .totalPrice(orderDto.getTotalPrice())
                .cart(
                        Cart.builder()
                                .cartId(orderDto.getCartDto().getCartId())
                                .build())
                .build();
    }

    public static OrderResponse mapProductResponse(List<OrderDto> content, Page<Order> products){
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setContent(content);
        orderResponse.setPageNo(products.getNumber());
        orderResponse.setPageSize(products.getSize());
        orderResponse.setTotalElements(products.getTotalElements());
        orderResponse.setTotalPages(products.getTotalPages());
        orderResponse.setLast(products.isLast());
        return orderResponse;
    }
}
