package com.bas.productservice.controller;

import com.bas.productservice.dto.ProductDTO;
import com.bas.productservice.exception.ProductNotFound;
import com.bas.productservice.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("test")
    public ResponseEntity<String>test(){
        return new ResponseEntity<>("productController", HttpStatus.OK);
    }

    @GetMapping("allProducts")
    public ResponseEntity<List<ProductDTO>>showAllProducts(){
        return new ResponseEntity<>(productService.showAllProducts(),HttpStatus.OK);
    }

    @GetMapping("product")
    public ResponseEntity<ProductDTO>findProductByName(@RequestParam String name) throws ProductNotFound {
        return new ResponseEntity<>(productService.findProductByName(name),HttpStatus.OK);
    }

    @PostMapping("add")
    public ResponseEntity<String>addProduct(@RequestBody @Valid ProductDTO productDTO){
        return new ResponseEntity<>(productService.addProduct(productDTO),HttpStatus.CREATED);
    }

    @DeleteMapping("delete")
    public ResponseEntity<String>deleteProduct(@RequestParam String name) throws ProductNotFound {
        return new ResponseEntity<>(productService.deleteProduct(name),HttpStatus.OK);
    }

}
