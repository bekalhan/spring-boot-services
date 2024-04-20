package com.bas.paymentservice.helper;

import com.bas.paymentservice.dto.OrderDto;
import com.bas.paymentservice.dto.PaymentDto;
import com.bas.paymentservice.entity.Payment;

public class PaymentMapperHelper {
    public static PaymentDto map(final Payment payment) {
        return PaymentDto.builder()
                .paymentId(payment.getPaymentId())
                .isPayed(payment.getIsPayed())
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
                .isPayed(paymentDto.getIsPayed())
                .paymentStatus(paymentDto.getPaymentStatus())
                .build();
    }

}
