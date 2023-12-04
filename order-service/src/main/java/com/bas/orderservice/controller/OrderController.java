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

    @GetMapping
    public ResponseEntity<DtoCollectionResponse<OrderDto>> findAll() {
        return ResponseEntity.ok(new DtoCollectionResponse<>(this.orderService.findAll()));
    }

    @GetMapping("/params")
    public ResponseEntity<OrderResponse> findAll(
            @RequestParam(value="pageNo",defaultValue = PageableConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
            @RequestParam(value="pageSize",defaultValue = PageableConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value="sortBy",defaultValue = PageableConstants.DEFAULT_SORT_BY,required = false) String sortBy,
            @RequestParam(value="sortDir",defaultValue = PageableConstants.DEFAULT_SORT_DIRECTION,required = false) String sortDir
    ) {
        var allProducts = orderService.getAllProductsWithPageableAndSorting(pageNo,pageSize,sortBy,sortDir);
        return ResponseEntity.status(HttpStatus.OK).body(allProducts);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> findById(@PathVariable("orderId")

                                             @Validated final Integer orderId) {

        log.info("OrderDto, resource; fetch order by id");
        return ResponseEntity.ok(this.orderService.findById(orderId));
    }

    @PostMapping

    public ResponseEntity<OrderDto> save(
                                         @RequestBody
                                         @Validated final OrderDto orderDto) {

            return ResponseEntity.ok(this.orderService.save(orderDto));


    }




    @PutMapping
    public ResponseEntity<OrderDto> update(@RequestBody

                                           @Validated final OrderDto orderDto) {
        return ResponseEntity.ok(this.orderService.update(orderDto));
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderDto> update(
                                           @PathVariable("orderId")
                                           @Validated final Integer orderId,
                                           @RequestBody
                                           @Validated final OrderDto orderDto) {


            return ResponseEntity.ok(this.orderService.update(orderId, orderDto));

    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("orderId") final Integer orderId) {

        this.orderService.deleteById(orderId);
        return ResponseEntity.ok(true);
    }
}
