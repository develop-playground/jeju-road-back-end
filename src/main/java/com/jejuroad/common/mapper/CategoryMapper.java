package com.jejuroad.common.mapper;

import com.jejuroad.common.BusinessException;
import com.jejuroad.domain.Category;
import com.jejuroad.dto.CategoryResponse;
import com.jejuroad.repository.CategoryRepository;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import static com.jejuroad.common.Message.RESTAURANT_RESPONSE_NONE_CATEGORY;

@Mapper(componentModel = "spring", uses = CategoryRepository.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class CategoryMapper {

    private CategoryRepository categoryRepository;

    public Category mapToCategoryFrom(String categoryName) {
        return categoryRepository
                .findByName(categoryName)
                .orElseThrow(() -> new BusinessException(RESTAURANT_RESPONSE_NONE_CATEGORY));
    }

    public String mapToStringFrom(Category category) {
        return category.getName();
    }

    public CategoryResponse.Find mapToFindFrom(Category category) {
        return CategoryResponse.Find.builder()
            .name(category.getName())
            .build();
    }

    @Autowired
    public void setCategoryRepository(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
}
