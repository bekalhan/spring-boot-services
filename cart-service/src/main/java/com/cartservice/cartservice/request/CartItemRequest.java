package com.cartservice.cartservice.request;

import com.cartservice.cartservice.entity.CartItemStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemRequest {

    private Long productId;
    private Long cartId;
    private int quantity;
    private Double totalPrice;
    private CartItemStatus status;
   // private LocalDateTime createdAt;

}
