package com.bas.paymentservice.Service;

import com.bas.paymentservice.Dto.PaymentDto;

import java.util.List;

public interface PaymentService {
    List<PaymentDto> findAll();
    PaymentDto findById(final Integer paymentId);
    PaymentDto save(final PaymentDto paymentDto);
    PaymentDto update(final PaymentDto paymentDto);
    void deleteById(final Integer paymentId);
}
