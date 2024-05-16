package com.bas.orderservice.controller;

import com.bas.orderservice.entity.Order;
import com.bas.orderservice.exception.OrderNotExist;
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

    @PostMapping("book")
    public ResponseEntity<Long>bookOrder(@RequestBody OrderRequest orderRequest) throws OrderNotExist {
        return new ResponseEntity<>(orderService.bookOrder(orderRequest),HttpStatus.CREATED);
    }

    @PutMapping("cancel")
    public ResponseEntity<String>cancelOrder(@RequestParam Long orderId){
        return new ResponseEntity<>(orderService.cancelOrder(orderId),HttpStatus.OK);
    }

    @PostMapping("allOrders")
    public ResponseEntity<List<Order>>viewOrders(@RequestBody List<Long>orderIds){
        return new ResponseEntity<>(orderService.viewOrders(orderIds),HttpStatus.OK);
    }

    @PostMapping("order")
    public ResponseEntity<List<Order>>viewOrderByStatus(@RequestBody List<Long>orderIds,@RequestParam String status){
        return new ResponseEntity<>(orderService.viewOrderByStatus(orderIds,status),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll() {
        log.info("*** OrderDto List, controller; fetch all orders *");
        return ResponseEntity.ok(this.orderService.findAll());
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
    public ResponseEntity<OrderRequest> save(
            @RequestBody
            @NotNull(message = "Input must not be NULL")
            @Valid final OrderRequest orderRequest) {
        log.info("*** OrderDto, resource; save order *");
        return ResponseEntity.ok(this.orderService.save(orderRequest));
    }

    @PutMapping
    public ResponseEntity<OrderResponse> update(
            @RequestBody
            @NotNull(message = "Input must not be NULL")
            @Valid final OrderRequest orderRequest) {
        log.info("*** OrderDto, resource; update order *");
        return ResponseEntity.ok(this.orderService.update(orderRequest));
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

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("orderId") final Long orderId) {
        log.info("*** Boolean, resource; delete order by id *");
        this.orderService.deleteById(orderId);
        return ResponseEntity.ok(true);
    }

}
