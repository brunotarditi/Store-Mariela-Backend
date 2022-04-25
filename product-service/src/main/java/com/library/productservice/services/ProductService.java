package com.library.productservice.services;

import com.library.productservice.entities.Product;
import com.library.productservice.repositories.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService{

    private final IProductRepository iProductRepository;

    @Autowired
    public ProductService(IProductRepository iProductRepository) {
        this.iProductRepository = iProductRepository;
    }

    @Override
    @Transactional
    public List<Product> findAllProducts(){
        List<Product> products = (List<Product>) this.iProductRepository.findAll();
        return (List<Product>) this.iProductRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Product> findByIdProduct(Long id){
        return this.iProductRepository.findById(id);
    }

    @Override
    public Product save(Product product){
        return this.iProductRepository.save(product);
    }
}
