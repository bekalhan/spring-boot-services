package com.cartservice.cartservice.request;

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
public class CartRequest {

    private Long productId;
    private Long userId;
    private int quantity;
    private Double totalPrice;
    private LocalDateTime createdAt;

}
