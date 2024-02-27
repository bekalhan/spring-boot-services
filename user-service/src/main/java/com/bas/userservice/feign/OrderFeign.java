package com.bas.userservice.feign;

import com.bas.userservice.dto.OrderDTO;
import jakarta.validation.constraints.NegativeOrZero;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("ORDER-SERVICE")
public interface OrderFeign {

    @PostMapping("order/action/book")
    ResponseEntity<Long> bookOrder(@RequestBody OrderDTO orderDTO);

    @PutMapping("order/action/cancel")
    ResponseEntity<String>cancelOrder(@RequestParam Long orderId);

    @PostMapping("order/action/allOrders")
    ResponseEntity<List<Order>>viewOrders(@RequestBody List<Long> orderIds);

    @PostMapping("order/action/order")
    ResponseEntity<List<Order>>viewOrderByStatus(@RequestBody List<Long> orderIds, @RequestParam String status);
}