package com.bas.orderservice.feign;

import com.bas.orderservice.dto.ProductDTO;
import com.bas.orderservice.exception.ProductNotExist;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("PRODUCT-SERVICE")
public interface ProductFeign {

    @GetMapping("product/action/product")
    ResponseEntity<ProductDTO> findProductByName(@RequestParam String name) throws ProductNotExist;
}