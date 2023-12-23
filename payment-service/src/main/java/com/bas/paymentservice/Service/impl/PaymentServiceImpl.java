package com.bas.paymentservice.Service.impl;

import com.bas.paymentservice.Constant.Constants;
import com.bas.paymentservice.Dto.OrderDto;
import com.bas.paymentservice.Dto.PaymentDto;
import com.bas.paymentservice.Exception.Wrapper.PaymentNotFoundException;
import com.bas.paymentservice.Helper.PaymentMapping;
import com.bas.paymentservice.Repository.PaymentRepository;
import com.bas.paymentservice.Service.PaymentService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final RestTemplate restTemplate;


    @Override
    public List<PaymentDto> findAll() {
        System.out.println("find"+paymentRepository.findAll());
        return this.paymentRepository.findAll()
                .stream()
                .map(PaymentMapping::map)
                .map(p -> {
                    System.out.println("p"+p);
                    p.setOrderDto(this.restTemplate.getForObject(Constants.Domains
                            .ORDER_SERVICE_API_URL + "/" + p.getOrderDto().getOrderId(), OrderDto.class));
                    return p;
                })
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public PaymentDto findById(Integer paymentId) {
        return this.paymentRepository.findById(paymentId)
                .map(PaymentMapping::map)
                .map(p -> {
                    p.setOrderDto(this.restTemplate.getForObject(Constants.Domains
                            .ORDER_SERVICE_API_URL + "/" + p.getOrderDto().getOrderId(), OrderDto.class));
                    return p;
                })
                .orElseThrow(() -> new PaymentNotFoundException(String.format("Payment with id: %d not found", paymentId)));
    }

    @Override
    public PaymentDto save(PaymentDto paymentDto) {
        return PaymentMapping.map(this.paymentRepository
                .save(PaymentMapping.map(paymentDto)));
    }

    @Override
    public PaymentDto update(PaymentDto paymentDto) {
        return PaymentMapping.map(this.paymentRepository
                .save(PaymentMapping.map(paymentDto)));
    }

    @Override
    public void deleteById(Integer paymentId) {
        this.paymentRepository.findById(paymentId)
                .orElseThrow(() -> new PaymentNotFoundException(String.format("Payment with id: %d not found", paymentId)));

        this.paymentRepository.deleteById(paymentId);
    }
}
