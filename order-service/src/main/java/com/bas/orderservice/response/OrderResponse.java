package com.bas.orderservice.response;

import com.bas.orderservice.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {

    private long orderId;
    private LocalDateTime createdAt;
    private Status orderStatus;
    private double totalPrice;
    private Integer totalQuantity;
    private PaymentResponse paymentResponse;
    private List<OrderItemResponse> orderItemResponse;
    private Long userId;
    private UserResponse userResponse;


}