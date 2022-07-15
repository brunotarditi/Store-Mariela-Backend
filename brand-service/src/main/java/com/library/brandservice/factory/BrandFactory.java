package com.library.brandservice.factory;

import com.library.brandservice.dtos.BrandDto;
import com.library.brandservice.entities.Brand;
import com.library.commonsservice.factory.IFactory;
import com.library.commonsservice.mapper.ModelMapperConfig;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BrandFactory extends ModelMapperConfig implements IFactory<Brand, BrandDto> {
    @Override
    public Brand createEntity(BrandDto brandDto) {
        return getModelMapper().map(brandDto, Brand.class);
    }

    @Override
    public BrandDto createDto(Brand brand) {
        return getModelMapper().map(brand, BrandDto.class);
    }

    public List<Brand> createEntitiesList(List<BrandDto> brandDtos){
        List<Brand> brands = new ArrayList<>();
        brandDtos.forEach(brandDto -> {
            brands.add(createEntity(brandDto));
        });
        return brands;
    }
}
