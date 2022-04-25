package com.library.categoryservice.services;

import com.library.categoryservice.entities.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    List<Category> findAllCategories();
    Optional<Category> findByIdCategory(Long id);
    Category save(Category category);
}
