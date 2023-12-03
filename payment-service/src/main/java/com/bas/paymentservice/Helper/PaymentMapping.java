package com.bas.paymentservice.Helper;

import com.bas.paymentservice.Dto.OrderDto;
import com.bas.paymentservice.Dto.PaymentDto;
import com.bas.paymentservice.Entity.Payment;

public class PaymentMapping {
    public static PaymentDto map(final Payment payment) {
        return PaymentDto.builder()
                .paymentId(payment.getPaymentId())
                .isPaid(payment.getIsPaid())
                .paymentStatus(payment.getPaymentStatus())
                .orderDto(
                        OrderDto.builder()
                                .orderId(payment.getOrderId())
                                .build())
                .build();
    }

    public static Payment map(final PaymentDto paymentDto) {
        return Payment.builder()
                .paymentId(paymentDto.getPaymentId())
                .orderId(paymentDto.getOrderDto().getOrderId())
                .isPaid(paymentDto.getIsPaid())
                .paymentStatus(paymentDto.getPaymentStatus())
                .build();
    }

}
