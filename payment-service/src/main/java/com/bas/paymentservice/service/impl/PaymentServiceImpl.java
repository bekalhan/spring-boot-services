package com.bas.paymentservice.service.impl;

import com.bas.paymentservice.constant.AppConstant;
import com.bas.paymentservice.dto.OrderDto;
import com.bas.paymentservice.dto.PaymentDto;
import com.bas.paymentservice.exception.PaymentNotFound;
import com.bas.paymentservice.helper.PaymentMapperHelper;
import com.bas.paymentservice.repository.PaymentRepository;
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
    public List<PaymentDto> findAll() {
        return this.paymentRepository.findAll()
                .stream()
                .map(PaymentMapperHelper::map)
                .map(p -> {
                    p.setOrderDto(this.restTemplate.getForObject(AppConstant.DiscoveredDomainsApi
                            .ORDER_SERVICE_API_URL + "/" + p.getOrderDto().getOrderId(), OrderDto.class));
                    return p;
                })
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public PaymentDto findById(Long paymentId) {
        return this.paymentRepository.findById(paymentId)
                .map(PaymentMapperHelper::map)
                .map(p -> {
                    p.setOrderDto(this.restTemplate.getForObject(AppConstant.DiscoveredDomainsApi
                            .ORDER_SERVICE_API_URL + "/" + p.getOrderDto().getOrderId(), OrderDto.class));
                    return p;
                })
                .orElseThrow(() -> new PaymentNotFound(String.format("Payment with id: %d not found", paymentId)));
    }

    @Override
    public PaymentDto save(PaymentDto paymentDto) {
        return PaymentMapperHelper.map(this.paymentRepository
                .save(PaymentMapperHelper.map(paymentDto)));
    }

    @Override
    public PaymentDto update(PaymentDto paymentDto) {
        return PaymentMapperHelper.map(this.paymentRepository
                .save(PaymentMapperHelper.map(paymentDto)));
    }

    @Override
    public void deleteById(Long paymentId) {
        this.paymentRepository.deleteById(paymentId);
    }
}
