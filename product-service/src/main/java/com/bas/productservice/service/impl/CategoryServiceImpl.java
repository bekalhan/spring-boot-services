package com.bas.productservice.service.impl;

import com.bas.productservice.dto.CategoryDto;
import com.bas.productservice.entity.Category;
import com.bas.productservice.exception.ResourceAlreadyExistException;
import com.bas.productservice.exception.ResourceNotFoundException;
import com.bas.productservice.helper.CategoryMapperHelper;
import com.bas.productservice.repository.CategoryRepository;
import com.bas.productservice.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    @Override
    public List<CategoryDto> getAllCategories() {
        var categories = categoryRepository.findAll();
        return categories.stream()
                .map(CategoryMapperHelper::map).collect(Collectors.toList())
                ;
    }

    @Override
    public CategoryDto getCategoryById(final Long categoryId) {
        return this.categoryRepository.findById(categoryId)
                .map(CategoryMapperHelper::map)
                .orElseThrow(() -> new ResourceNotFoundException("Category","id", categoryId));
    }

    @Override
    public CategoryDto createCategory(final CategoryDto categoryDto) {
        var categoryExist = categoryRepository.existsByTitle(categoryDto.getTitle());
        if(categoryExist) throw new ResourceAlreadyExistException("Category","title",categoryDto.getTitle());
        return CategoryMapperHelper.map(this.categoryRepository
                .save(CategoryMapperHelper.map(categoryDto)));
    }

    @Override
    public CategoryDto patchCategory(final Long categoryId,final CategoryDto categoryDto) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("category","id",categoryId));

        if (categoryDto.getParentCategoryDto() == null) {
            categoryDto.setParentCategoryDto(CategoryMapperHelper.map(category.getParentCategory()));
        }

        Category updatedCategory = CategoryMapperHelper.map(categoryDto);
        updatedCategory.setCategoryId(categoryId);
        updatedCategory.setParentCategory(category.getParentCategory());

        return CategoryMapperHelper.map(this.categoryRepository.save(updatedCategory));
    }

    @Override
    public void deleteCategory(final Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("category","id",categoryId));
        this.categoryRepository.deleteById(categoryId);
    }


}
