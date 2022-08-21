package com.library.categoryservice.repositories;

import com.library.categoryservice.entities.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICategoryRepository extends PagingAndSortingRepository<Category, Long> {
    @Query(value = "SELECT c FROM Category c WHERE c.isEnabled = true")
    List<Category> getCategoriesEnabled();
}
