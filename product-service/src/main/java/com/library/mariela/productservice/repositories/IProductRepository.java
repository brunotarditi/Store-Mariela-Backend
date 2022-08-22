package com.library.mariela.productservice.repositories;

import com.library.mariela.productservice.entities.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends PagingAndSortingRepository<Product, Long> {
    @Query(value = "SELECT p FROM Product p WHERE p.categoryId = :categoryId and p.isEnabled = true")
    List<Product> findProductByCategoryIdEnabled(Long categoryId);

    @Query(value = "SELECT p FROM Product p WHERE p.brandId = :brandId and p.isEnabled = true")
    List<Product> findProductByBrandIdEnabled(Long brandId);
}
