package com.library.productservice.services;

import com.library.commonsservice.services.ICommonService;
import com.library.productservice.dtos.ProductDto;
import com.library.productservice.entities.Product;

import java.util.List;

public interface IProductService extends ICommonService<Product, ProductDto> {
    List<Product> findByBrandId(Long brandId);
}
