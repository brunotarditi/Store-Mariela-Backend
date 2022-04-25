package com.library.brandservice.services;

import com.library.brandservice.entities.Brand;
import com.library.brandservice.repositories.IBrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService implements IBrandService{

    private final IBrandRepository brandRepository;

    @Autowired
    public BrandService(IBrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public List<Brand> findAllBrands() {
        return (List<Brand>) this.brandRepository.findAll();
    }

    @Override
    public Optional<Brand> findByIdBrand(Long id) {
        return this.brandRepository.findById(id);
    }

    @Override
    public Brand save(Brand brand) {
        return this.brandRepository.save(brand);
    }
}
