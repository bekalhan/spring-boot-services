package com.abs.proxyclient.business.order.service;

import com.abs.proxyclient.business.order.model.OrderDto;
import com.abs.proxyclient.business.order.model.response.OrderOrderServiceDtoCollectionResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "ORDER-SERVICE", contextId = "orderClientService", path = "/order-service/api/orders")
public interface OrderClientService {
    @GetMapping
    public ResponseEntity<OrderOrderServiceDtoCollectionResponse> findAll();

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> findById(
            @PathVariable("orderId")
            @NotBlank(message = "Input must not be blank!")
            @Valid final String orderId);

    @PostMapping
    public ResponseEntity<OrderDto> save(
            @RequestBody
            @NotNull(message = "Input must not be NULL!")
            @Valid final OrderDto orderDto);

    @PutMapping
    public ResponseEntity<OrderDto> update(
            @RequestBody
            @NotNull(message = "Input must not be NULL!")
            @Valid final OrderDto orderDto);

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderDto> update(
            @PathVariable("orderId")
            @NotBlank(message = "Input must not be blank!")
            @Valid final String orderId,
            @RequestBody
            @NotNull(message = "Input must not be NULL!")
            @Valid final OrderDto orderDto);

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("orderId") final String orderId);

}
