package com.bas.orderservice.response;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class CreditCard {

    private String nameOnCard;
    private String cardNumber;
    private String expiryDate;
    private String cvv;
}
