package com.library.categoryservice.services;

import com.library.categoryservice.dtos.CategoryDto;
import com.library.categoryservice.entities.Category;
import com.library.commonsservice.services.ICommonService;

public interface ICategoryService extends ICommonService<Category, CategoryDto> {
}
