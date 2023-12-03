package com.bas.productservice.service;

import com.bas.productservice.dto.CategoryDto;
import com.bas.productservice.entity.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAllCategories();
    CategoryDto getCategoryById(final Long categoryId);
    CategoryDto createCategory(final CategoryDto categoryDto);
    CategoryDto patchCategory(final Long categoryId,final CategoryDto categoryDto);
    void deleteCategory(final Long categoryId);
}
