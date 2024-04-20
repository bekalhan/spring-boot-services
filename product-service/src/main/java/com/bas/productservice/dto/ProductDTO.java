package com.bas.productservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        name = "Products",
        description = "Schema to hold Product information"
)
public class ProductDTO {
    private Long productId;
    @NotNull(message = "title should not be null or empty")
    private String name;
    @NotNull(message = "price should not be null or empty")
    private Double price;
    @NotBlank(message = "description cannot be empty")
    private String description;
    private String imageUrl;
    @NotNull(message = "quantity should not be null or empty")
    private Integer quantity;
    @NotNull(message = "categoryId must not be null")
    private CategoryDto categoryDto;
}
