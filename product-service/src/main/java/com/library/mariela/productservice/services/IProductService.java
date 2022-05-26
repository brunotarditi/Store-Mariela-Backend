package com.library.mariela.productservice.services;

import com.library.commonsservice.services.ICommonService;
import com.library.mariela.productservice.dtos.ProductDto;
import com.library.mariela.productservice.entities.Product;

import java.util.List;

public interface IProductService extends ICommonService<Product, ProductDto> {
    List<ProductDto> getProductBrandById(Long brandId);
}
