package com.bas.paymentservice.helper;


import com.bas.paymentservice.entity.Payment;
import com.bas.paymentservice.request.PaymentRequest;
import com.bas.paymentservice.response.PaymentResponse;

public class PaymentMapperHelper {
    public static PaymentResponse paymentToPaymentResponse(final Payment payment) {
        return null ;
//        return PaymentRequest.builder()
//                .paymentId(payment.getPaymentId())
//                .isPayed(payment.getIsPayed())
//                .paymentStatus(payment.getPaymentStatus())
//                .orderDto(
//                        OrderDto.builder()
//                                .orderId(payment.getOrderId())
//                                .build())
//                .build();
    }

    public static Payment paymentRequestToPayment(final PaymentRequest paymentDto) {
        return null;
//        Payment.builder()
//                .paymentId(paymentDto.getPaymentId())
//                .orderId(paymentDto.getOrderDto().getOrderId())
//                .isPayed(paymentDto.getIsPayed())
//                .paymentStatus(paymentDto.getPaymentStatus())
//                .build();
    }

}
