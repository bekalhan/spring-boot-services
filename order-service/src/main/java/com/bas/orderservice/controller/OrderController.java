package com.bas.orderservice.controller;

import com.bas.orderservice.constant.PageableConstants;
import com.bas.orderservice.dto.OrderDto;
import com.bas.orderservice.dto.OrderResponse;
import com.bas.orderservice.dto.reponse.DtoCollectionResponse;
import com.bas.orderservice.entity.Order;
import com.bas.orderservice.service.OrderService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@Slf4j
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

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
