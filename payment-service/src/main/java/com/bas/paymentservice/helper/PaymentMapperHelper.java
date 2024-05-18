package com.bas.paymentservice.helper;


import com.bas.paymentservice.entity.CreditCard;
import com.bas.paymentservice.entity.Payment;
import com.bas.paymentservice.request.PaymentRequest;
import com.bas.paymentservice.response.PaymentResponse;

public class PaymentMapperHelper {
    public static PaymentResponse paymentToPaymentResponse(final Payment payment) {

        return PaymentResponse.builder()
                .paymentId(payment.getPaymentId())
                .creditCard(payment.getCreditCard())
                .shippingAddress(payment.getShippingAddress())
                .userId(payment.getUserId())
                .build();
    }

    public static Payment paymentRequestToPayment( PaymentRequest paymentRequest) {

        return Payment.builder()
                .shippingAddress(paymentRequest.getShippingAddress())
                .userId(paymentRequest.getUserId())
                .creditCard(CreditCard.builder()
                        .cardNumber(paymentRequest.getCreditCard().getCardNumber())
                        .cvv(paymentRequest.getCreditCard().getCvv())
                        .expiryDate(paymentRequest.getCreditCard().getExpiryDate())
                        .nameOnCard(paymentRequest.getCreditCard().getNameOnCard())
                        .build())
                .build();
    }

}
