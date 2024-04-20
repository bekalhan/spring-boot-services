package com.bas.paymentservice.service;

import com.bas.paymentservice.dto.PaymentDto;

import java.util.List;

public interface PaymentService {
    List<PaymentDto> findAll();
    PaymentDto findById(final Long paymentId);
    PaymentDto save(final PaymentDto paymentDto);
    PaymentDto update(final PaymentDto paymentDto);
    void deleteById(final Long paymentId);
}
