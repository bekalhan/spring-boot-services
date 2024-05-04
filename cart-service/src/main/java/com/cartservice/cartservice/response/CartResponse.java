package com.cartservice.cartservice.response;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class CartResponse {

    private Long cartId;
    private ProductResponse product;
    private UserResponse user;
    private int quantity;
    private Double totalPrice;
    private LocalDateTime createdAt;

}
