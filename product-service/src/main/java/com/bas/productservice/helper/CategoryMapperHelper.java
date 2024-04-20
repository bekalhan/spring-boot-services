package com.bas.productservice.helper;

import com.bas.productservice.dto.CategoryDto;
import com.bas.productservice.dto.CategoryRequest;
import com.bas.productservice.entity.Category;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

public class CategoryMapperHelper {
    public static CategoryDto map(final Category category) {
        final var parentCategory = Optional.ofNullable(category
                .getParentCategory()).orElseGet(() -> new Category());



       // System.out.println("bak"+category);

        return CategoryDto.builder()
                .categoryId(category.getCategoryId())
                .title(category.getTitle())
                .subCategoriesDtos(
                        Optional.ofNullable(category.getSubCategories())
                                .orElse(Collections.emptySet())
                                .stream()
                                .map(sub -> CategoryMapperHelper.map(sub))
                                .collect(Collectors.toSet())
                )
                .build();
    }

    public static Category map(final CategoryRequest categoryDto) {
        return Category.builder()
                .title(categoryDto.getTitle())
                .parentCategory(
                        Optional.ofNullable(categoryDto.getCategory())
                                .map(parentCategoryDto -> Category.builder()
                                        .categoryId(parentCategoryDto.getCategoryId())
                                        .title(parentCategoryDto.getTitle())
                                        .build())
                                .orElse(null)
                )
                .build();
    }

    public static CategoryDto mapWithProducts(final Category category) {
        CategoryDto categoryDto = map(category);
        return categoryDto;
    }
}
