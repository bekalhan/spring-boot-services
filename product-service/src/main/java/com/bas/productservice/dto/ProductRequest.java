package com.bas.productservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private Long productId;
    @NotNull(message = "title should not be null or empty")
    private String name;
    @NotNull(message = "price should not be null or empty")
    private Double price;
    @NotBlank(message = "description cannot be empty")
    private String description;
    private String imageUrl;
    private Integer quantity;
    @NotNull(message = "categoryId must not be null")
    private Long categoryId;
    private Long subCategoryId;
    private Map<String, String> dynamicFields;

    private String brand;
    private String color;
}
