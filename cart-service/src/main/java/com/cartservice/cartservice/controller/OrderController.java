package com.cartservice.cartservice.controller;

import com.cartservice.cartservice.request.OrderRequest;
import com.cartservice.cartservice.response.BasicOrderResponse;
import com.cartservice.cartservice.response.OrderResponse;
import com.cartservice.cartservice.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cart/order/")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders(){
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @GetMapping("getOrder/{orderId}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable("orderId") Long orderId){
        return new ResponseEntity<>(orderService.getOrderById(orderId),HttpStatus.OK);
    }

    @PostMapping("createOrder")
    public ResponseEntity<Long> createOrder(@RequestBody@Valid OrderRequest orderRequest){
        return new ResponseEntity<>(orderService.createOrder(orderRequest),HttpStatus.CREATED);
    }

    @PutMapping("updateOrder/{orderId}")
    public ResponseEntity<BasicOrderResponse> updateOrder(@PathVariable("orderId") Long orderId, @RequestBody@Valid OrderRequest orderRequest){
        return new ResponseEntity<>(orderService.updateOrder(orderId,orderRequest),HttpStatus.OK);
    }

    @DeleteMapping("deleteOrder/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable("orderId") Long orderId){
        return new ResponseEntity<>(orderService.deleteOrder(orderId),HttpStatus.OK);
    }
}
