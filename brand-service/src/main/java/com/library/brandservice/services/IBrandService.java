package com.library.brandservice.services;

import com.library.brandservice.entities.Brand;

import java.util.List;
import java.util.Optional;

public interface IBrandService {
    List<Brand> findAllBrands();
    Optional<Brand> findByIdBrand(Long id);
    Brand save(Brand brand);
}
