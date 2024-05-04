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
public class OrderRequest {

    private long productId;
    private Status status;
    private double orderPrice;
    private long quantity;
    //private long cartId;
    //private PaymentMode paymentMode;
}