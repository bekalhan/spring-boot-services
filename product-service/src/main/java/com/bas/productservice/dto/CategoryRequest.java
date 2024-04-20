package com.bas.productservice.dto;


import com.bas.productservice.entity.Category;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest {
    @NotEmpty(message = "title should not be null or empty")
    private String title;

    @JsonProperty("parentCategoryId")
    private Long parentCategoryId;

    @JsonProperty("parentCategory")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CategoryDto parentCategoryDto;

    private Category category;

}
