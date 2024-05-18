package com.bas.paymentservice.entity;

import jakarta.persistence.*;
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
