package com.cartservice.cartservice.response;

import lombok.*;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class BasicCartResponse {
    private Long cartId;
    private Long productId;
    private Long userId;
    private int quantity;
    private Double totalPrice;
    private LocalDateTime createdAt;
}
