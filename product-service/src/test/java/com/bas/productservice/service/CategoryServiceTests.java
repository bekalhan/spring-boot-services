package com.bas.productservice.service;

import com.bas.productservice.dto.CategoryDto;
import com.bas.productservice.entity.Category;
import com.bas.productservice.exception.ResourceNotFoundException;
import com.bas.productservice.helper.CategoryMapperHelper;
import com.bas.productservice.repository.CategoryRepository;
import com.bas.productservice.service.impl.CategoryServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.util.*;
import java.util.stream.Collectors;

public class CategoryServiceTests {
    private CategoryRepository categoryRepository;
    private CategoryService categoryService;

    private Category category;
    private CategoryMapperHelper categoryMapperHelper;

    @BeforeEach
    public void setUp(){
        categoryRepository = Mockito.mock(CategoryRepository.class);
        categoryMapperHelper = Mockito.mock(CategoryMapperHelper.class);
        categoryService = new CategoryServiceImpl(categoryRepository);
    }

    @Test
    public void givenEmptyCategoryList_whenGetAllCategories_thenReturnCategoriesList(){
        generateCategory();

        //given
        BDDMockito.given(categoryRepository.findAll()).willReturn(List.of(generateCategory()));

        //when
        List<Category> categoriesList = categoryRepository.findAll();

        //then
        Assertions.assertThat(categoriesList).isNotNull();
        Assertions.assertThat(categoriesList.size()).isEqualTo(1);

    }

    @Test
    public void givenCategoryListWithParentCategoryIdNullRecord_whenGetAllCategories_thenReturnCategoriesDtoList(){
        //given
        Category category2 = Category.builder()
                .categoryId(5L)
                .title("test")
                .parentCategory(null)
                .build();
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(generateCategory());
        categoryList.add(category2);

        BDDMockito.given(categoryRepository.findAll()).willReturn(categoryList);
        //when
        List<CategoryDto> categoryListDto = categoryService.getAllCategories();


        //then
        List<CategoryDto> expected = new ArrayList<>(){{
            add(CategoryMapperHelper.map(category2));
        }};
        Assertions.assertThat(categoryListDto).isNotNull();
        Assertions.assertThat(categoryListDto.size()).isEqualTo(1);
    }

    @Test
    public void givenCategoryId_whenGetCategoryById_thenReturnCategoryDto(){
        //given
        generateCategory();
        BDDMockito.given(categoryRepository.findById(1L)).willReturn(Optional.of(generateCategory()));
        //when
        Category category = categoryRepository.findById(generateCategory().getCategoryId()).get();
        CategoryDto categoryDto = CategoryMapperHelper.map(category);
        //then
        Assertions.assertThat(categoryDto).isNotNull();
    }

    private Category generateCategory(){
        Category category1 = Category.builder()
                .categoryId(2L)
                .title("computer")
                .build();

        Category category2 = Category.builder()
                .categoryId(3L)
                .title("phone")
                .build();

        Category category3 = Category.builder()
                .categoryId(4L)
                .title("tv")
                .build();

        Set<Category> categories = new HashSet<Category>(){{
            add(category2);
            add(category3);
        }};

        return Category.builder()
                .categoryId(1L)
                .title("Furniture")
                .subCategories(categories)
                .parentCategory(category1)
                .build();
    }

    private CategoryDto generateCategoryDto(){
        CategoryDto parentCategory1 = CategoryDto.builder()
                .categoryId(2L)
                .title("computer")
                .build();

        CategoryDto subCategory2 = CategoryDto.builder()
                .categoryId(3L)
                .title("phone")
                .build();

        CategoryDto subCategory3 = CategoryDto.builder()
                .categoryId(4L)
                .title("tv")
                .build();

        Set<CategoryDto> subCategories = new HashSet<CategoryDto>(){{
            add(subCategory2);
            add(subCategory3);
        }};

        return CategoryDto.builder()
                .categoryId(1L)
                .title("Furniture")
                .subCategoriesDtos(subCategories)
                .parentCategoryDto(parentCategory1)
                .build();

    }
}
