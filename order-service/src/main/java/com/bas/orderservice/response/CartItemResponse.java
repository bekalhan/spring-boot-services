package com.bas.orderservice.response;

import com.bas.orderservice.entity.CartItemStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemResponse {
    private Long cartItemId;
    private ProductResponse product;
    private Long cartId;
    private int quantity;
    private Double totalPrice;
    private CartItemStatus status;
    private LocalDateTime createdAt;
}
