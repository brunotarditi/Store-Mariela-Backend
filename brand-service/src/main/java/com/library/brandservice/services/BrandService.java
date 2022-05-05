package com.library.brandservice.services;

import com.library.brandservice.dtos.BrandDto;
import com.library.brandservice.entities.Brand;
import com.library.brandservice.factory.BrandFactory;
import com.library.brandservice.repositories.IBrandRepository;
import com.library.commonsservice.services.CommonService;
import org.springframework.stereotype.Service;

@Service
public class BrandService extends CommonService<Brand, IBrandRepository, BrandDto> implements IBrandService {

    public BrandService(IBrandRepository iBrandRepository, BrandFactory brandFactory) {
        super(iBrandRepository, brandFactory);
    }
}
