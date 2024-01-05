package com.abs.proxyclient.business.payment.service;

import com.abs.proxyclient.business.payment.model.PaymentDto;
import com.abs.proxyclient.business.payment.model.response.PaymentPaymentServiceDtoCollectionResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "PAYMENT-SERVICE", contextId = "paymentClientService", path = "/payment-service/api/payments")
public interface PaymentClientService {

    @GetMapping
    public ResponseEntity<PaymentPaymentServiceDtoCollectionResponse> findAll();

    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentDto> findById(
            @PathVariable("paymentId")
            @NotBlank(message = "Input must not be blank!")
            @Valid final String paymentId);

    @PostMapping
    public ResponseEntity<PaymentDto> save(
            @RequestBody
            @NotNull(message = "Input must not be NULL!")
            @Valid final PaymentDto paymentDto);

    @PutMapping
    public ResponseEntity<PaymentDto> update(
            @RequestBody
            @NotNull(message = "Input must not be NULL!")
            @Valid final PaymentDto paymentDto);

    @DeleteMapping("/{paymentId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("paymentId") final String paymentId);

}