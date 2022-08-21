package com.library.brandservice.services;

import com.library.brandservice.clients.IProductFeignClient;
import com.library.brandservice.dtos.BrandDto;
import com.library.brandservice.entities.Brand;
import com.library.brandservice.factory.BrandFactory;
import com.library.brandservice.dtos.ProductDto;
import com.library.brandservice.repositories.IBrandRepository;
import com.library.commonsservice.services.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrandService extends CommonService<Brand, IBrandRepository, BrandDto> implements IBrandService {
    private final IProductFeignClient productFeignClient;
    private final BrandFactory brandFactory;
    @Autowired
    public BrandService(IBrandRepository iBrandRepository, BrandFactory brandFactory, IProductFeignClient productFeignClient) {
        super(iBrandRepository, brandFactory);
        this.productFeignClient = productFeignClient;
        this.brandFactory = brandFactory;
    }

    @Override
    public List<BrandDto> getBrandsEnabled() {
        List<Brand> brands = repository.getBrandsEnabled();
        return brands
                .stream()
                .map(iFactory::createDto)
                .collect(Collectors.toList());
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
        List<ProductDto> productDtos = productFeignClient.getProductsByBrandId(brandId);
        if (productDtos.isEmpty())
            results.put("Products", "No existen los productos");
        else
            results.put("Products", productDtos);
        return results;
    }

    @Override
    public List<Brand> saveAll(List<BrandDto> brandDtos) {
        List<Brand> brands = this.brandFactory.createEntitiesList(brandDtos);
        return (List<Brand>) this.repository.saveAll(brands);
    }

    @Override
    public List<ProductDto> getProductByBrandId(Long brandId) {
        return productFeignClient.getProductsByBrandId(brandId);
    }
}
