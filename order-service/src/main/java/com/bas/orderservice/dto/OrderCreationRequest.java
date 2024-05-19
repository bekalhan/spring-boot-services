package com.bas.orderservice.dto;

import com.bas.orderservice.request.CartRequest;
import com.bas.orderservice.request.OrderRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderCreationRequest {
    private OrderRequest orderRequest;
    private CartRequest cartRequest;

}
