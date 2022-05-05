package com.library.categoryservice.services;

import com.library.categoryservice.dtos.CategoryDto;
import com.library.categoryservice.entities.Category;
import com.library.categoryservice.factory.CategoryFactory;
import com.library.categoryservice.repositories.ICategoryRepository;
import com.library.commonsservice.services.CommonService;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends CommonService<Category, ICategoryRepository, CategoryDto> implements ICategoryService{

    public CategoryService(ICategoryRepository iCategoryRepository, CategoryFactory categoryFactory) {
        super(iCategoryRepository, categoryFactory);
    }
}
