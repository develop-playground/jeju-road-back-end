package com.jejuroad.common.mapper;

import com.jejuroad.domain.Category;
import com.jejuroad.repository.CategoryRepository;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = CategoryRepository.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class CategoryMapper {

    private CategoryRepository categoryRepository;

    public Category mapToCategoryFrom(String categoryName) {
        return categoryRepository.findByName(categoryName);
    }

    public String mapToStringFrom(Category category) {
        return category.getName();
    }

    @Autowired
    public void setCategoryRepository(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
}