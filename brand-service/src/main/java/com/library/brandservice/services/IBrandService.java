package com.library.brandservice.services;

import com.library.brandservice.dtos.BrandDto;
import com.library.brandservice.dtos.ProductDto;
import com.library.brandservice.entities.Brand;
import com.library.commonsservice.services.ICommonService;

import java.util.List;
import java.util.Map;

public interface IBrandService extends ICommonService<Brand, BrandDto> {
    List<BrandDto> getBrandsEnabled();
    Map<String, Object> getBrandsWithProducts(Long brandId);
    List<Brand> saveAll(List<BrandDto> brandDtos);
    List<ProductDto> getProductByBrandId(Long brandId);
}
