package com.bas.paymentservice.response;

import com.bas.paymentservice.entity.CreditCard;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class PaymentResponse {
    private Long paymentId;
    private Long userId;
    @OneToOne(cascade = CascadeType.ALL)
    private CreditCard creditCard;
    private String shippingAddress;
}
