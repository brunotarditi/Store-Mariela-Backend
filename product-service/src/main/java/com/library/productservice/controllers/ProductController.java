package com.library.productservice.controllers;

import com.library.commonsservice.controllers.CommonController;
import com.library.productservice.dtos.ProductDto;
import com.library.productservice.entities.Product;
import com.library.productservice.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController extends CommonController<Product, ProductDto, IProductService> {

    @Autowired
    public ProductController(IProductService iProductService) {
        super(iProductService);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody ProductDto productDto, BindingResult result, @PathVariable Long id){
        if (result.hasErrors())
            return this.validate(result);
        Optional<ProductDto> productOptional = commonService.findById(id);
        if (productOptional.isEmpty())
            return new ResponseEntity<>("Producto no encontrado.", HttpStatus.NOT_FOUND);
        productOptional.get().setName(productDto.getName());
        productOptional.get().setPrice(productDto.getPrice());
        productOptional.get().setUpdateAt(new Date());
        productOptional.get().setBrandId(productDto.getBrandId());
        productOptional.get().setCategoryId(productDto.getCategoryId());

        return new ResponseEntity<>(commonService.save(productOptional.get()), HttpStatus.OK);
    }

    @GetMapping("/byBrand/{brandId}")
    public ResponseEntity<?> getProductsByBrandId(@PathVariable long brandId) {
        List<Product> products = this.commonService.findByBrandId(brandId);
        return ResponseEntity.ok(products);
    }
}
