package com.library.categoryservice.services;

import com.library.categoryservice.entities.Category;
import com.library.categoryservice.repositories.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService{

    private final ICategoryRepository iCategoryRepository;

    @Autowired
    public CategoryService(ICategoryRepository iCategoryRepository) {
        this.iCategoryRepository = iCategoryRepository;
    }

    @Override
    public List<Category> findAllCategories() {
        return (List<Category>) this.iCategoryRepository.findAll();
    }

    @Override
    public Optional<Category> findByIdCategory(Long id) {
        return this.iCategoryRepository.findById(id);
    }

    @Override
    public Category save(Category category) {
        return this.iCategoryRepository.save(category);
    }
}
