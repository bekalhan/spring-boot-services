package com.bas.orderservice.controller;

import com.bas.orderservice.dto.OrderDTO;
import com.bas.orderservice.entity.Order;
import com.bas.orderservice.exception.ProductNotFound;
import com.bas.orderservice.feign.ProductFeign;
import com.bas.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@Slf4j
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final ProductFeign productFeign;

    @GetMapping("test")
    public ResponseEntity<String>test(){
        return new ResponseEntity<>("orderController", HttpStatus.OK);
    }

    @PostMapping("book")
    public ResponseEntity<Long>bookOrder(@RequestBody OrderDTO orderDTO) throws ProductNotFound {
        return new ResponseEntity<>(orderService.bookOrder(orderDTO),HttpStatus.CREATED);
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

}
