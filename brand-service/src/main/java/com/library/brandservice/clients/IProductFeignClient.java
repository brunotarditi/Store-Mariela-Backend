package com.library.brandservice.clients;

import com.library.brandservice.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name = "products-service")
public interface IProductFeignClient {

    @GetMapping("/api/products/byBrand/{brandId}")
    List<Product> getProductsByBrandId(@PathVariable long brandId);
}
