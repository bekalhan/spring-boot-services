package com.bas.paymentservice.service;


import com.bas.paymentservice.request.PaymentRequest;
import com.bas.paymentservice.response.PaymentResponse;

import java.util.List;

public interface PaymentService {
    List<PaymentResponse> findAll();
    PaymentResponse findById(final Long paymentId);
    PaymentResponse save(final PaymentRequest paymentDto);
    PaymentResponse update(final PaymentRequest paymentDto);
    void deleteById(final Long paymentId);
}
