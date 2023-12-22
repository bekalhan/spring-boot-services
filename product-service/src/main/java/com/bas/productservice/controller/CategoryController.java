package com.bas.productservice.controller;

import com.bas.productservice.constant.ProductConstants;
import com.bas.productservice.dto.CategoryDto;
import com.bas.productservice.dto.ErrorResponseDto;
import com.bas.productservice.dto.ResponseDto;
import com.bas.productservice.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "CRUD REST APIs for Products in abs",
        description = "CRUD REST APIs in abs to CREATE, UPDATE, FETCH AND DELETE category details"
)
@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    @Autowired
    private final CategoryService categoryService;

   @Operation(
            summary = "Fetch categories  REST API",
            description = "REST API to fetch Category"
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
    public ResponseEntity<List<CategoryDto>> findAll() {
        var allCategories = categoryService.getAllCategories();
        return ResponseEntity.status(HttpStatus.OK).body(allCategories);
    }

    @Operation(
            summary = "Fetch Category Details REST API",
            description = "REST API to fetch Category Details"
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
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> findById(
            @PathVariable("categoryId") Long categoryId) {
        CategoryDto categoryDto = categoryService.getCategoryById(categoryId);
        return ResponseEntity.status(HttpStatus.OK).
                body(categoryDto);
    }

    @Operation(
            summary = "Save Account REST API",
            description = "REST API to create new category"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
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
            @Valid final CategoryDto categoryDto) {
        System.out.println("controller"+categoryDto);
        var createdCategory = categoryService.createCategory(categoryDto);
        System.out.println("controller2"+createdCategory);
        return ResponseEntity.status(HttpStatus.CREATED).
                body(new ResponseDto(ProductConstants.STATUS_200, ProductConstants.MESSAGE_201_CATEGORY,createdCategory));
    }

    @Operation(
            summary = "Update Category Details REST API",
            description = "REST API to update Category"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
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
    @PutMapping("/{categoryId}")
    public ResponseEntity<ResponseDto> update(
            @PathVariable("categoryId") Long categoryId,
            @RequestBody
            @Valid final CategoryDto categoryDto) {
        var updatedCategory = categoryService.patchCategory(categoryId,categoryDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(ProductConstants.STATUS_200, ProductConstants.MESSAGE_200,updatedCategory));
    }

    @Operation(
            summary = "Delete Category",
            description = "REST API to delete Category"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
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
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteById(@PathVariable("categoryId") final Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.status(HttpStatus.OK).body("Category deleted successfully");
    }


}
