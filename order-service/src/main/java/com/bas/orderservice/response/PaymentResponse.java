package com.bas.orderservice.response;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponse {
    private Long paymentId;
    private Long userId;
    @OneToOne(cascade = CascadeType.ALL)
    private CreditCard creditCard;
    private String shippingAddress;
}
