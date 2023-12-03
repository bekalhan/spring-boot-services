package com.bas.productservice.controller;

import com.bas.productservice.constant.PageableAndSortingConstants;
import com.bas.productservice.constant.ProductConstants;
import com.bas.productservice.dto.CategoryDto;
import com.bas.productservice.dto.ErrorResponseDto;
import com.bas.productservice.dto.ProductDto;
import com.bas.productservice.dto.ResponseDto;
import com.bas.productservice.response.ProductResponse;
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
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @Operation(
            summary = "Fetch products REST API",
            description = "REST API to fetch Products"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping
    public ResponseEntity<List<ProductDto>> findAll(){
        var allProducts = productService.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(allProducts);
    }

    @Operation(
            summary = "Fetch products according to REST API",
            description = "REST API to fetch products"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/params")
    public ResponseEntity<ProductResponse> findAll(
            @RequestParam(value="pageNo",defaultValue = PageableAndSortingConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
            @RequestParam(value="pageSize",defaultValue = PageableAndSortingConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value="sortBy",defaultValue = PageableAndSortingConstants.DEFAULT_SORT_BY,required = false) String sortBy,
            @RequestParam(value="sortDir",defaultValue = PageableAndSortingConstants.DEFAULT_SORT_DIRECTION,required = false) String sortDir
        ) {
        var allProducts = productService.getAllProductsWithPageableAndSorting(pageNo,pageSize,sortBy,sortDir);
        return ResponseEntity.status(HttpStatus.OK).body(allProducts);
    }

    @Operation(
            summary = "Fetch product details REST API",
            description = "REST API to fetch Products details"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> findById(
            @PathVariable("productId")
            @Valid final Long productId) {
        var productDto = productService.getProductById(productId);
        return ResponseEntity.status(HttpStatus.OK).body(productDto);
    }

    @Operation(
            summary = "save products REST API",
            description = "REST API to save Products"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PostMapping
    public ResponseEntity<ResponseDto> save(
            @RequestBody
            @Valid final ProductDto productDto) {
        var createdProduct = productService.createProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).
                body(new ResponseDto(ProductConstants.STATUS_200, ProductConstants.MESSAGE_201_PRODUCT,createdProduct));
    }

    @Operation(
            summary = "Update products REST API",
            description = "REST API to save Update"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
   @PutMapping("/{productId}")
    public ResponseEntity<ResponseDto> update(
            @PathVariable("productId")
            @Valid final Long productId,
            @RequestBody
            @Valid final ProductDto productDto) {
        var updatedProduct = productService.patchProduct(productId,productDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(ProductConstants.STATUS_200, ProductConstants.MESSAGE_200,updatedProduct));
    }

    @Operation(
            summary = "delete products REST API",
            description = "REST API to save delete"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteById(@PathVariable("productId") final Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully");
    }

}
