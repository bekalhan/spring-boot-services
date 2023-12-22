package com.bas.productservice.helper;

import com.bas.productservice.dto.CategoryDto;
import com.bas.productservice.dto.ProductDto;
import com.bas.productservice.entity.Category;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public interface CategoryMapperHelper {
    public static CategoryDto map(final Category category) {
        final var parentCategory = Optional.ofNullable(category
                .getParentCategory()).orElseGet(() -> new Category());

        System.out.println("bak"+category);

        return CategoryDto.builder()
                .categoryId(category.getCategoryId())
                .title(category.getTitle())
                .imageUrl(category.getImageUrl())
                .productDtos(Optional.ofNullable(category.getProducts())
                        .orElse(Collections.emptySet())
                        .stream()
                        .map(product -> ProductMapperHelper.map(product))
                        .collect(Collectors.toSet()))                .parentCategoryDto(
                        CategoryDto.builder()
                                .categoryId(parentCategory.getCategoryId())
                                .title(parentCategory.getTitle())
                                .imageUrl(parentCategory.getImageUrl())
                                .build())
                .build();
    }

    public static Category map(final CategoryDto categoryDto) {
        System.out.println("dto to category "+categoryDto);
        return Category.builder()
                .categoryId(categoryDto.getCategoryId())
                .title(categoryDto.getTitle())
                .imageUrl(categoryDto.getImageUrl())
                .parentCategory(
                        Optional.ofNullable(categoryDto.getParentCategoryDto())
                                .map(parentCategoryDto -> Category.builder()
                                        .categoryId(parentCategoryDto.getCategoryId())
                                        .title(parentCategoryDto.getTitle())
                                        .imageUrl(parentCategoryDto.getImageUrl())
                                        .build())
                                .orElse(null)
                )
                .build();
    }

    public static CategoryDto mapWithProducts(final Category category) {
        CategoryDto categoryDto = map(category);
        categoryDto.setProductDtos(ProductMapperHelper.mapList(category.getProducts()));
        return categoryDto;
    }


}
