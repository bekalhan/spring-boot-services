package com.bas.orderservice.controller;

import com.bas.orderservice.dto.OrderCreationRequest;
import com.bas.orderservice.entity.Order;
import com.bas.orderservice.entity.Status;
import com.bas.orderservice.exception.OrderNotExist;
import com.bas.orderservice.request.CartRequest;
import com.bas.orderservice.request.OrderRequest;
import com.bas.orderservice.response.OrderResponse;
import com.bas.orderservice.service.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order/")
@Slf4j
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    //private final ProductFeign productFeign;

    @GetMapping("test")
    public ResponseEntity<String>test(){
        return new ResponseEntity<>("orderController", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll() {
        log.info("*** OrderDto List, controller; fetch all orders *");
        return ResponseEntity.ok(this.orderService.findAll());
    }
    @GetMapping("findByUserId/{userId}")
    public ResponseEntity<List<OrderResponse>> findByUserId(@PathVariable("userId") Long userId){
        return ResponseEntity.ok(this.orderService.findByUserId(userId));
    }
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> findById(
            @PathVariable("orderId")
            @NotBlank(message = "Input must not be blank")
            @Valid final Long orderId) {
        log.info("*** OrderDto, resource; fetch order by id *");
        return ResponseEntity.ok(this.orderService.findById(orderId));
    }

    @PostMapping
    public Long save(
            @RequestBody
            @NotNull(message = "Input must not be NULL")
            @Valid final OrderCreationRequest orderCreationRequest) {
        log.info("*** OrderDto, resource; save order *");
        return (this.orderService.create(orderCreationRequest.getOrderRequest(), orderCreationRequest.getCartRequest()));
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderResponse> update(
            @PathVariable("orderId")
            @NotBlank(message = "Input must not be blank")
            @Valid final Long orderId,
            @RequestBody
            @NotNull(message = "Input must not be NULL")
            @Valid final OrderRequest orderRequest) {
        log.info("*** OrderDto, resource; update order with orderId *");
        return ResponseEntity.ok(this.orderService.update(orderId, orderRequest));
    }

    @PutMapping("/changeStatus/{orderId}")
    public ResponseEntity<String> changeStatus(
            @PathVariable("orderId")
            @Valid final Long orderId,
            @RequestBody
            @Valid final OrderRequest orderRequest) {
        log.info("*** String, resource; change order status by orderId *");
        this.orderService.changeStatus(orderId,orderRequest);
        return ResponseEntity.ok("Order status changed successfully");
    }
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("orderId") final Long orderId) {
        log.info("*** Boolean, resource; delete order by id *");
        this.orderService.deleteById(orderId);
        return ResponseEntity.ok(true);
    }

}
