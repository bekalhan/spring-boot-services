package com.cartservice.cartservice.response;

import com.cartservice.cartservice.entity.CartItemStatus;
import lombok.*;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class BasicCartItemResponse {
    private Long cartItemId;
    private Long cartId;
    private CartItemStatus status;
    private int quantity;
    private Double totalPrice;
    private LocalDateTime createdAt;
}
