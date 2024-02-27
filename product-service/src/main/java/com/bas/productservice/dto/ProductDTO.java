package com.bas.productservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        name = "Products",
        description = "Schema to hold Product information"
)
public class ProductDto {
    private Long productId;
    @Schema(
            description = "Name of the product", example = "macbook"
    )
    @NotNull(message = "title should not be null or empty")
    private String title;
    private String imageUrl;
    @Schema(
            description = "Stock keep Unit", example = "3213"
    )
    private String sku;
    @Schema(
            description = "price of the product", example = "1000$"
    )
    @NotNull(message = "price should not be null or empty")
    private Double price;
    @Schema(
            description = "count of the product", example = "56"
    )
    @NotNull(message = "quantity should not be null or empty")
    private Integer quantity;

    @NotNull(message = "categoryId must not be null")
    private Long categoryId;
}
