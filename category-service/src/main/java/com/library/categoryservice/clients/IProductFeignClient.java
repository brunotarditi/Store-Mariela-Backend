package com.library.categoryservice.clients;

import com.library.categoryservice.dtos.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "products-service")
public interface IProductFeignClient {
    @GetMapping("/api/products/byCategory/{categoryId}")
    List<ProductDto> getProductsByCategoryId(@PathVariable Long categoryId);
}
