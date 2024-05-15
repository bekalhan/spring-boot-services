package com.cartservice.cartservice.response;

import com.cartservice.cartservice.entity.Status;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class CartResponse {
    private Long cartId;
    private List<CartItemResponse> cartItems;
    private UserResponse userId;
    private int totalQuantity;
    private Double cartPrice;
    private Status status;
    private LocalDateTime createdAt;
}
