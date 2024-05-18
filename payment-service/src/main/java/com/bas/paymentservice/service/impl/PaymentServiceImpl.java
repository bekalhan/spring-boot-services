package com.bas.paymentservice.service.impl;

import com.bas.paymentservice.constant.AppConstant;

import com.bas.paymentservice.entity.Payment;
import com.bas.paymentservice.exception.PaymentNotFound;
import com.bas.paymentservice.helper.PaymentMapperHelper;
import com.bas.paymentservice.repository.PaymentRepository;
import com.bas.paymentservice.request.PaymentRequest;
import com.bas.paymentservice.response.PaymentResponse;
import com.bas.paymentservice.service.PaymentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final RestTemplate restTemplate;


    @Override
    public List<PaymentResponse> findAll() {
        return this.paymentRepository.findAll().stream().map(PaymentMapperHelper::paymentToPaymentResponse).collect(Collectors.toList());
    }

    @Override
    public PaymentResponse findById(Long paymentId) {
        Payment payment  = paymentRepository.findById(paymentId).orElseThrow(() -> new PaymentNotFound(String.format("Payment with id: %d not found", paymentId)));
        PaymentResponse paymentResponse = PaymentMapperHelper.paymentToPaymentResponse(payment);
        return paymentResponse;
    }

    @Override
    public List<PaymentResponse> findPaymentByUserId(Long userId) {
        return this.paymentRepository.findPaymentByUserId(userId).stream().map(PaymentMapperHelper::paymentToPaymentResponse).collect(Collectors.toList());
    }


    @Override
    public PaymentResponse save(PaymentRequest paymentRequest) {
        Payment isPaymentExistWithUser = this.paymentRepository.findPaymentByUserId(paymentRequest.getUserId()).orElse(null);
        if(isPaymentExistWithUser != null)
        {
           return update(paymentRequest, isPaymentExistWithUser.getPaymentId());
        }
        else
        {
            return PaymentMapperHelper.paymentToPaymentResponse(this.paymentRepository
                    .save(PaymentMapperHelper.paymentRequestToPayment(paymentRequest)));
        }

    }

    @Override
    public PaymentResponse update(PaymentRequest paymentRequest , Long paymentId) {

        Payment payment = this.paymentRepository.findById(paymentId).orElseThrow(() -> new PaymentNotFound(String.format("Payment with id: %d not found", paymentId)));
        payment.setCreditCard(paymentRequest.getCreditCard());
        payment.setShippingAddress(paymentRequest.getShippingAddress());
        return PaymentMapperHelper.paymentToPaymentResponse(this.paymentRepository.save(payment));
    }

    @Override
    public void deleteById(Long paymentId) {
        this.paymentRepository.deleteById(paymentId);
    }
}
