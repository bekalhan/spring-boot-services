package com.bas.productservice.controller;

import com.bas.productservice.constant.ProductConstants;
import com.bas.productservice.dto.CategoryDto;
import com.bas.productservice.dto.ResponseDto;
import com.bas.productservice.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

        @GetMapping
    public ResponseEntity<List<CategoryDto>> findAll() {
        var allCategories = categoryService.getAllCategories();
        return ResponseEntity.status(HttpStatus.OK).body(allCategories);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> findById(@PathVariable("categoryId") Long categoryId) {
        CategoryDto categoryDto = categoryService.getCategoryById(categoryId);
        return ResponseEntity.status(HttpStatus.OK).
                body(categoryDto);
    }

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

    @PutMapping("/{categoryId}")
    public ResponseEntity<ResponseDto> update(@PathVariable("categoryId") Long categoryId, @RequestBody @Valid final CategoryDto categoryDto) {
        var updatedCategory = categoryService.updateCategory(categoryId,categoryDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(ProductConstants.STATUS_200, ProductConstants.MESSAGE_200,updatedCategory));
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteById(@PathVariable("categoryId") final Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.status(HttpStatus.OK).body("Category deleted successfully");
    }



}
