package com.bas.orderservice.response;

import com.bas.orderservice.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartResponse {
    private Long cartId;
    private List<CartItemResponse> cartItems;
    private int totalQuantity;
    private Double cartPrice;
    private Status status;
    private LocalDateTime createdAt;
}
