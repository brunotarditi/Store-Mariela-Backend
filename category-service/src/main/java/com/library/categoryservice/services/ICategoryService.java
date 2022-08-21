package com.library.categoryservice.services;

import com.library.categoryservice.dtos.CategoryDto;
import com.library.categoryservice.dtos.ProductDto;
import com.library.categoryservice.entities.Category;
import com.library.commonsservice.services.ICommonService;

import java.util.List;
import java.util.Map;

public interface ICategoryService extends ICommonService<Category, CategoryDto> {
    List<CategoryDto> getCategoriesEnabled();
    Map<String, Object> getCategoriesWithProducts(Long brandId);
    List<ProductDto> getProductByCategoryId(Long categoryId);
}
