package com.library.productservice.controllers;

import com.library.commonsservice.controllers.CommonController;
import com.library.productservice.entities.Product;
import com.library.productservice.services.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController extends CommonController<Product, IProductService> {

    public ProductController(IProductService iProductService) {
        super(iProductService);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Product product, @PathVariable Long id){
        Optional<Product> productOptional = commonService.findById(id);
        if (productOptional.isEmpty())
            return new ResponseEntity<>("Producto no encontrado.", HttpStatus.NOT_FOUND);
        productOptional.get().setName(product.getName());
        productOptional.get().setPrice(product.getPrice());
        productOptional.get().setBrandId(product.getBrandId());

        return new ResponseEntity<>(commonService.save(productOptional.get()), HttpStatus.OK);
    }
}
