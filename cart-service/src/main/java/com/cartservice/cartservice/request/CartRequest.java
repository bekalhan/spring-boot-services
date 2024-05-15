package com.cartservice.cartservice.request;

import com.cartservice.cartservice.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartRequest {

    private Long userId;
    private Status status;
    private Double cartPrice;
    private Integer totalQuantity;
}
