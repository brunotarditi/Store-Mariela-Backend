package com.library.categoryservice.factory;

import com.library.categoryservice.dtos.CategoryDto;
import com.library.categoryservice.entities.Category;
import com.library.commonsservice.factory.IFactory;
import com.library.commonsservice.mapper.ModelMapperConfig;
import org.springframework.stereotype.Component;

@Component
public class CategoryFactory extends ModelMapperConfig implements IFactory<Category, CategoryDto> {
    @Override
    public Category createEntity(CategoryDto categoryDto) {
        return getModelMapper().map(categoryDto, Category.class);
    }

    @Override
    public CategoryDto createDto(Category category) {
        return getModelMapper().map(category, CategoryDto.class);
    }
}
