package com.abs.proxyclient.business.product.service;

import com.abs.proxyclient.business.product.model.CategoryDto;
import com.abs.proxyclient.business.product.model.response.CategoryProductServiceCollectionDtoResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "PRODUCT-SERVICE", contextId = "categoryClientService", path = "/product-service/api/categories")
public interface CategoryClientService {

    @GetMapping
    ResponseEntity<CategoryProductServiceCollectionDtoResponse> findAll();

    @GetMapping("/{categoryId}")
    ResponseEntity<CategoryDto> findById(
            @PathVariable("categoryId")
            @NotBlank(message = "Input must not be blank!")
            @Valid final String categoryId);

    @PostMapping
    ResponseEntity<CategoryDto> save(
            @RequestBody
            @NotNull(message = "Input must not be NULL!")
            @Valid final CategoryDto categoryDto);

    @PutMapping
    ResponseEntity<CategoryDto> update(
            @RequestBody
            @NotNull(message = "Input must not be NULL!")
            @Valid final CategoryDto categoryDto);

    @PutMapping("/{categoryId}")
    ResponseEntity<CategoryDto> update(
            @PathVariable("categoryId")
            @NotBlank(message = "Input must not be blank!")
            @Valid final String categoryId,
            @RequestBody
            @NotNull(message = "Input must not be NULL!")
            @Valid final CategoryDto categoryDto);

    @DeleteMapping("/{categoryId}")
    ResponseEntity<Boolean> deleteById(@PathVariable("categoryId") final String categoryId);

}