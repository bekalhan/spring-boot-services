package com.cartservice.cartservice.response;

import com.cartservice.cartservice.entity.CartStatus;
import lombok.*;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class BasicCartResponse {
    private Long cartId;
    private CartStatus status;
    private int quantity;
    private Double totalPrice;
    private LocalDateTime createdAt;
}
