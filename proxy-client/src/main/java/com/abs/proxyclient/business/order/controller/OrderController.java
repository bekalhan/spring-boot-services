package com.abs.proxyclient.business.order.controller;

import com.abs.proxyclient.business.order.model.OrderDto;
import com.abs.proxyclient.business.order.model.response.OrderOrderServiceDtoCollectionResponse;
import com.abs.proxyclient.business.order.service.OrderClientService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderClientService orderClientService;

    @GetMapping
    public ResponseEntity<OrderOrderServiceDtoCollectionResponse> findAll() {
        return ResponseEntity.ok(this.orderClientService.findAll().getBody());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> findById(
            @PathVariable("orderId")
            @NotBlank(message = "Input must not be blank!")
            @Valid final String orderId) {
        return ResponseEntity.ok(this.orderClientService.findById(orderId).getBody());
    }

    @PostMapping
    public ResponseEntity<OrderDto> save(
            @RequestBody
            @NotNull(message = "Input must not be NULL!")
            @Valid final OrderDto orderDto) {
        return ResponseEntity.ok(this.orderClientService.save(orderDto).getBody());
    }
    @PutMapping
    public ResponseEntity<OrderDto> update(
            @RequestBody
            @NotNull(message = "Input must not be NULL!")
            @Valid final OrderDto orderDto) {
        return ResponseEntity.ok(this.orderClientService.update(orderDto).getBody());
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderDto> update(
            @PathVariable("orderId")
            @NotBlank(message = "Input must not be blank!")
            @Valid final String orderId,
            @RequestBody
            @NotNull(message = "Input must not be NULL!")
            @Valid final OrderDto orderDto) {
        return ResponseEntity.ok(this.orderClientService.update(orderId, orderDto).getBody());
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("orderId") final String orderId) {
        this.orderClientService.deleteById(orderId).getBody();
        return ResponseEntity.ok(true);
    }
}
