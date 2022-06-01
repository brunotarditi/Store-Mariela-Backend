package com.library.mariela.productservice.controllers;

import com.library.commonsservice.controllers.CommonController;
import com.library.mariela.productservice.dtos.ProductDto;
import com.library.mariela.productservice.entities.Product;
import com.library.mariela.productservice.models.HistoricalPurchase;
import com.library.mariela.productservice.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
        productOptional.get().setCreateAt(productOptional.get().getCreateAt());
        productOptional.get().setUpdateAt(new Date());
        productOptional.get().setBrandId(productDto.getBrandId());
        productOptional.get().setCategoryId(productDto.getCategoryId());

        return new ResponseEntity<>(commonService.save(productOptional.get()), HttpStatus.OK);
    }

    @GetMapping("/byBrand/{brandId}")
    public ResponseEntity<?> getProductsByBrandId(@PathVariable Long brandId) {
        List<ProductDto> products = this.commonService.getProductBrandById(brandId);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/all/{productId}")
    public ResponseEntity<?> getAll(@PathVariable Long productId) {
        Map<String, Object> results = this.commonService.getProductsWithStocksAndPurchases(productId);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @PostMapping("/savePurchase/{productId}")
    public ResponseEntity<?> saveTeacher(@PathVariable Long productId, @RequestBody HistoricalPurchase purchase) {
        if (this.commonService.findById(productId).isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(this.commonService.savePurchase(productId, purchase));
    }
}
