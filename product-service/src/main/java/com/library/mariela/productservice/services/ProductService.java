package com.library.mariela.productservice.services;

import com.library.commonsservice.services.CommonService;
import com.library.mariela.productservice.factories.ProductFactory;
import com.library.mariela.productservice.dtos.ProductDto;
import com.library.mariela.productservice.entities.Product;
import com.library.mariela.productservice.repositories.IProductRepository;
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
