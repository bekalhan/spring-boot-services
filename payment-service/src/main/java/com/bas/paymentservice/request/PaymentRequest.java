package com.bas.paymentservice.request;

import com.bas.paymentservice.entity.CreditCard;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToOne;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class PaymentRequest {
    private Long userId;
    private CreditCard creditCard;
    private String shippingAddress;
}
