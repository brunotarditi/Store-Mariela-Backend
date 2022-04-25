package com.library.productservice.services;

import com.library.productservice.entities.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product> findAllProducts();
    Optional<Product> findByIdProduct(Long id);
    Product save(Product product);
}
