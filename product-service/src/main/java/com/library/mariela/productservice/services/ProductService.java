package com.library.mariela.productservice.services;

import com.library.commonsservice.services.CommonService;
import com.library.mariela.productservice.factories.ProductFactory;
import com.library.mariela.productservice.dtos.ProductDto;
import com.library.mariela.productservice.entities.Product;
import com.library.mariela.productservice.repositories.IProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService extends CommonService<Product, IProductRepository, ProductDto> implements IProductService {

    public ProductService(IProductRepository iProductRepository, ProductFactory productFactory) {
        super(iProductRepository, productFactory);
    }
    @Override
    public List<ProductDto> getProductBrandById(Long brandId) {
        List<Product> products = this.repository.findProductByBrandId(brandId);
        return products
                .stream()
                .map(iFactory::createDto)
                .collect(Collectors.toList());
    }

}
