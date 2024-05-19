package com.bas.orderservice.request;

import com.bas.orderservice.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartRequest {

    private Long cartId;
    private Long userId;
    private Double cartPrice;
    private Integer totalQuantity;
}
