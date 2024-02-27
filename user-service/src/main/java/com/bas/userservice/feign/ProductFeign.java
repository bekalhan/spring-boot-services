package com.bas.userservice.feign;


import com.bas.userservice.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient("PRODUCT-SERVICE")
public interface ProductFeign {

    @GetMapping("product/action/product")
    ResponseEntity<ProductDTO> findProductByName(@RequestParam String name);

    @GetMapping("product/action/allProducts")
    ResponseEntity<List<ProductDTO>>showAllProducts();
}