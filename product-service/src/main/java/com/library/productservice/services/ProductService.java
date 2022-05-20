package com.library.productservice.services;

import com.library.commonsservice.services.CommonService;
import com.library.productservice.dtos.ProductDto;
import com.library.productservice.entities.Product;
import com.library.productservice.factory.ProductFactory;
import com.library.productservice.repositories.IProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService extends CommonService<Product, IProductRepository, ProductDto> implements IProductService {

    public ProductService(IProductRepository iProductRepository, ProductFactory productFactory) {
        super(iProductRepository, productFactory);
    }
    @Override
    public List<Product> findByBrandId(Long brandId) {
        return this.repository.findProductByBrandId(brandId);
    }

}
