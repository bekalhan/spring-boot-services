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
    @NotBlank(message = "Name cannot be empty")
    private String name;
    @NotNull(message = "Price cannot be empty")
    @Min(value = 1000,message = "Product price cannot be less than 1000")
    private Long price;
    @NotBlank(message = "description cannot be empty")
    private String description;
}
