package com.library.productservice.services;

import com.library.commonsservice.services.CommonService;
import com.library.productservice.entities.Product;
import com.library.productservice.repositories.IProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends CommonService<Product, IProductRepository> implements IProductService{

    public ProductService(IProductRepository iProductRepository) {
        super(iProductRepository);
    }
}
