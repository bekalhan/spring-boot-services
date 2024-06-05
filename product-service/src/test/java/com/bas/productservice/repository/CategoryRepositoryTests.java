package com.bas.productservice.repository;

import com.bas.productservice.entity.Category;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoryRepositoryTests {
    @Autowired
    private CategoryRepository categoryRepository;

    private Category category;

    @BeforeEach
    public void setup(){
        category = Category.builder()
                .title("tech")
                .build();
    }

    @Test
    public void givenCategoryObject_whenSave_thenReturnCategoryObject(){
        //given

        //when
        Category saveCategory = categoryRepository.save(category);

        //then
        Assertions.assertThat(saveCategory).isNotNull();
        Assertions.assertThat(saveCategory.getCategoryId()).isGreaterThan(0);
    }
}
