package com.bas.shippingservice.controller;

import com.bas.shippingservice.dto.OrderItemDto;
import com.bas.shippingservice.dto.response.collection.DtoCollectionResponse;
import com.bas.shippingservice.entity.id.OrderItemId;
import com.bas.shippingservice.service.OrderItemService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shippings")
@Slf4j
@RequiredArgsConstructor
public class OrderItemController {
    private final OrderItemService orderItemService;

    @GetMapping
    public ResponseEntity<DtoCollectionResponse<OrderItemDto>> findAll() {
        return ResponseEntity.ok(new DtoCollectionResponse<>(this.orderItemService.findAll()));
    }

    @GetMapping("/{orderId}/{productId}")
    public ResponseEntity<OrderItemDto> findById(
            @PathVariable("orderId") final String orderId,
            @PathVariable("productId") final String productId) {
        return ResponseEntity.ok(this.orderItemService.findById(
                new OrderItemId(Integer.parseInt(orderId), Integer.parseInt(productId))));
    }

    @GetMapping("/find")
    public ResponseEntity<OrderItemDto> findById(
            @RequestBody
            @NotNull
            @Valid final OrderItemId orderItemId) {
        return ResponseEntity.ok(this.orderItemService.findById(orderItemId));
    }

    @PostMapping
    public ResponseEntity<OrderItemDto> save(
            @RequestBody
            @NotNull(message = "Input must not be NULL")
            @Valid final OrderItemDto orderItemDto) {
        return ResponseEntity.ok(this.orderItemService.save(orderItemDto));
    }

    @PutMapping
    public ResponseEntity<OrderItemDto> update(
            @RequestBody
            @NotNull(message = "Input must not be NULL")
            @Valid final OrderItemDto orderItemDto) {
        return ResponseEntity.ok(this.orderItemService.update(orderItemDto));
    }

    @DeleteMapping("/{orderId}/{productId}")
    public ResponseEntity<Boolean> deleteById(
            @PathVariable("orderId") final String orderId,
            @PathVariable("productId") final String productId) {
        this.orderItemService.deleteById(new OrderItemId(Integer.parseInt(orderId), Integer.parseInt(productId)));
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteById(
            @RequestBody
            @NotNull(message = "Input must not be NULL")
            @Valid final OrderItemId orderItemId) {
        this.orderItemService.deleteById(orderItemId);
        return ResponseEntity.ok(true);
    }
}
