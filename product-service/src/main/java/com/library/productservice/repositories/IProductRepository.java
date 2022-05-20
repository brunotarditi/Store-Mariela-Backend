package com.library.productservice.repositories;

import com.library.productservice.entities.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends PagingAndSortingRepository<Product, Long> {

    List<Product> findProductByBrandId(Long brandId);
}
