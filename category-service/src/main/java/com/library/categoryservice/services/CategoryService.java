package com.library.categoryservice.services;

import com.library.categoryservice.clients.IProductFeignClient;
import com.library.categoryservice.dtos.CategoryDto;
import com.library.categoryservice.dtos.ProductDto;
import com.library.categoryservice.entities.Category;
import com.library.categoryservice.factory.CategoryFactory;
import com.library.categoryservice.repositories.ICategoryRepository;
import com.library.commonsservice.services.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService extends CommonService<Category, ICategoryRepository, CategoryDto> implements ICategoryService{
    private final IProductFeignClient productFeignClient;
    @Autowired
    public CategoryService(ICategoryRepository iCategoryRepository, CategoryFactory categoryFactory, IProductFeignClient productFeignClient) {
        super(iCategoryRepository, categoryFactory);
        this.productFeignClient = productFeignClient;
    }

    @Override
    public List<CategoryDto> getCategoriesEnabled() {
        List<Category> categories = this.repository.getCategoriesEnabled();
        return categories
                .stream()
                .map(iFactory::createDto)
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, Object> getCategoriesWithProducts(Long categoryId) {
        Map<String, Object> results = new HashMap<>();
        Optional<CategoryDto> categoryDto = this.findById(categoryId);
        if (categoryDto.isEmpty()) {
            results.put("Message", "No existe la marca");
            return results;
        }
        results.put("Category", categoryDto);
        List<ProductDto> productDtos = productFeignClient.getProductsByCategoryId(categoryId);
        if (productDtos.isEmpty())
            results.put("Products", "No existen los productos");
        else
            results.put("Products", productDtos);
        return results;
    }

    @Override
    public List<ProductDto> getProductByCategoryId(Long categoryId) {
        return productFeignClient.getProductsByCategoryId(categoryId);
    }
}
