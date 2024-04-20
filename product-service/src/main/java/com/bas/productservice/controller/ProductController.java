package com.bas.productservice.controller;

import com.bas.productservice.constant.ProductConstants;
import com.bas.productservice.dto.ErrorResponseDto;
import com.bas.productservice.dto.ProductDTO;
import com.bas.productservice.dto.ProductRequest;
import com.bas.productservice.dto.ResponseDto;
import com.bas.productservice.exception.ProductNotFound;
import com.bas.productservice.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product/action")
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
    @GetMapping("product/{productId}")
    public ResponseEntity<ProductDTO>findProductByName(@PathVariable("productId") final Long productId) throws ProductNotFound {
        return new ResponseEntity<>(productService.findProductId(productId),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductDTO>addProduct(@RequestBody @Valid ProductRequest productDTO){
        return new ResponseEntity<>(productService.addProduct(productDTO),HttpStatus.CREATED);
    }
    @PutMapping("/{productId}")
    public ResponseEntity<ResponseDto> update(
            @PathVariable("productId")
            @Valid final Long productId,
            @RequestBody
            @Valid final ProductDTO productDto) {
        var updatedProduct = productService.patchProduct(productId,productDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(ProductConstants.STATUS_200, ProductConstants.MESSAGE_200,updatedProduct));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteById(@PathVariable("productId") final Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully");
    }

    @GetMapping("/{subCategoryId}")
    public ResponseEntity<List<ProductDTO>> getProductsBySubCategoryId(@PathVariable(value = "subCategoryId") Long subCategoryId){
        List<ProductDTO> allProducts = productService.showAllProductsBySubCategoryId(subCategoryId);
        return new ResponseEntity<>(allProducts,HttpStatus.OK);
    }

}
