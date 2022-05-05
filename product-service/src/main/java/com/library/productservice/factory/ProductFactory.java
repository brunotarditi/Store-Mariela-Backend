package com.library.productservice.factory;

import com.library.commonsservice.factory.IFactory;
import com.library.commonsservice.mapper.ModelMapperConfig;
import com.library.productservice.dtos.ProductDto;
import com.library.productservice.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductFactory extends ModelMapperConfig implements IFactory<Product, ProductDto> {
    @Override
    public Product createEntity(ProductDto productDto) {
        return getModelMapper().map(productDto, Product.class);
    }

    @Override
    public ProductDto createDto(Product product) {
        return getModelMapper().map(product, ProductDto.class);
    }
}
