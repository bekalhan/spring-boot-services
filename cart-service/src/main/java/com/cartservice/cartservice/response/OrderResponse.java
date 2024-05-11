package com.cartservice.cartservice.response;

import com.cartservice.cartservice.entity.CartStatus;
import com.cartservice.cartservice.entity.Status;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class OrderResponse {
    private Long orderId;
    private List<CartResponse> carts;
    private int totalQuantity;
    private Double orderPrice;
    private Status status;
    private LocalDateTime createdAt;
}
