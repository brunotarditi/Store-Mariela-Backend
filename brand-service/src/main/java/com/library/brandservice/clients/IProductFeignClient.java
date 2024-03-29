package com.library.brandservice.clients;

import com.library.brandservice.dtos.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name = "products-service")
public interface IProductFeignClient {

    @GetMapping("/api/products/byBrand/{brandId}")
    List<ProductDto> getProductsByBrandId(@PathVariable Long brandId);
}
