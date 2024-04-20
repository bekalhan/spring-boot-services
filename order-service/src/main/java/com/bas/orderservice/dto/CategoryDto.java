package com.bas.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {
    private Integer categoryId;
    private String categoryTitle;
    private String imageUrl;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<CategoryDto> subCategoriesDtos;

    @JsonProperty("parentCategory")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CategoryDto parentCategoryDto;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<ProductDTO> productDtos;
}
