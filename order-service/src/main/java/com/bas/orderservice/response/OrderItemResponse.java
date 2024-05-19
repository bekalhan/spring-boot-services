package com.bas.orderservice.response;

import com.bas.orderservice.entity.CartItemStatus;
import com.bas.orderservice.entity.Order;
import com.bas.orderservice.entity.OrderItem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemResponse {
    private Long orderItemId;
    private ProductResponse product;
    private Long orderId;
    private Double totalPrice;
    private Integer quantity;

}
