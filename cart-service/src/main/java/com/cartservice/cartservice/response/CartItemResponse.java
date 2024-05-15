package com.cartservice.cartservice.response;

import com.cartservice.cartservice.entity.CartItemStatus;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class CartItemResponse {

    private Long cartItemId;
    private ProductResponse product;
    private Long cartId;
    private int quantity;
    private Double totalPrice;
    private CartItemStatus status;
    private LocalDateTime createdAt;

}
