package com.bas.productservice.service.impl;

import com.bas.productservice.dto.CategoryDto;
import com.bas.productservice.dto.CategoryRequest;
import com.bas.productservice.entity.Category;
import com.bas.productservice.exception.CategoryNotFound;
import com.bas.productservice.exception.ItemAlreadyExist;
import com.bas.productservice.exception.ResourceAlreadyExistException;
import com.bas.productservice.exception.ResourceNotFoundException;
import com.bas.productservice.helper.CategoryMapperHelper;
import com.bas.productservice.repository.CategoryRepository;
import com.bas.productservice.service.CategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional

public class CategoryServiceImpl implements CategoryService {
    private  final CategoryRepository categoryRepository;
    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        var categories = categoryRepository.findAll();
        return categories.stream()
                .filter((el) -> el.getParentCategory() == null)
                .map(CategoryMapperHelper::map).collect(Collectors.toList());

    }

    @Override
    public CategoryDto getCategoryById(Long categoryId) {
        return this.categoryRepository.findById(categoryId)
                .map(CategoryMapperHelper::map)
                .orElseThrow(() -> new ResourceNotFoundException("Category","id", categoryId));
    }

    @Override
    public CategoryDto createCategory(CategoryRequest categoryDto) {
        System.out.println("id "+categoryDto.getParentCategoryId());
        var categoryExist = categoryRepository.existsByTitle(categoryDto.getTitle());
        if(categoryExist) throw new ResourceAlreadyExistException("Category","title",categoryDto.getTitle());

        if(categoryDto.getParentCategoryId() != null){
            Category category = categoryRepository.findById(categoryDto.getParentCategoryId())
                            .orElseThrow(()-> new ResourceNotFoundException("id","parent category id",categoryDto.getParentCategoryId()));
            categoryDto.setCategory(category);
        }
        return CategoryMapperHelper.map(this.categoryRepository
                .save(CategoryMapperHelper.map(categoryDto)));
    }

    @Override
    public CategoryDto updateCategory(Long categoryId, CategoryDto categoryDto) {

        Category existingCategory = categoryRepository.findById(categoryDto.getCategoryId())
                .orElseThrow(() -> new CategoryNotFound("Category not found with id: " + categoryDto.getCategoryId()));

        BeanUtils.copyProperties(categoryDto, existingCategory, "categoryId", "parentCategoryDto");


        return CategoryMapperHelper.map(categoryRepository.save(existingCategory));
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("category","id",categoryId));
        this.categoryRepository.deleteById(categoryId);
    }
}
