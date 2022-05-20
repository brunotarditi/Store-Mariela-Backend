package com.library.brandservice.services;

import com.library.brandservice.dtos.BrandDto;
import com.library.brandservice.entities.Brand;
import com.library.commonsservice.services.ICommonService;

import java.util.Map;

public interface IBrandService extends ICommonService<Brand, BrandDto> {

    Map<String, Object> getBrandsWithProducts(Long brandId);
}
