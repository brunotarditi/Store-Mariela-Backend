package com.library.brandservice.services;

import com.library.brandservice.clients.ProductFeignClient;
import com.library.brandservice.dtos.BrandDto;
import com.library.brandservice.entities.Brand;
import com.library.brandservice.factory.BrandFactory;
import com.library.brandservice.models.Product;
import com.library.brandservice.repositories.IBrandRepository;
import com.library.commonsservice.services.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BrandService extends CommonService<Brand, IBrandRepository, BrandDto> implements IBrandService {
    private final ProductFeignClient productFeignClient;

    @Autowired
    public BrandService(IBrandRepository iBrandRepository, BrandFactory brandFactory, ProductFeignClient productFeignClient) {
        super(iBrandRepository, brandFactory);
        this.productFeignClient = productFeignClient;
    }

    @Override
    public Map<String, Object> getBrandsWithProducts(Long brandId) {
        Map<String, Object> results = new HashMap<>();
        Optional<BrandDto> brandDto = this.findById(brandId);
        if (brandDto.isEmpty()) {
            results.put("Message", "No existe la marca");
            return results;
        }
        results.put("Brand", brandDto);
        List<Product> products = productFeignClient.getProductsByBrandId(brandId);
        if (products.isEmpty())
            results.put("Products", "No existen los productos");
        else
            results.put("Products", products);
        return results;
    }
}
