package com.library.brandservice.services;

import com.library.brandservice.entities.Brand;
import com.library.brandservice.repositories.IBrandRepository;
import com.library.commonsservice.services.CommonService;
import org.springframework.stereotype.Service;

@Service
public class BrandService extends CommonService<Brand, IBrandRepository> implements IBrandService {

    public BrandService(IBrandRepository iBrandRepository) {
        super(iBrandRepository);
    }
}
