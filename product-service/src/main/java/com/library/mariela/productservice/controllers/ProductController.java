package com.library.mariela.productservice.controllers;

import com.library.commonsservice.controllers.CommonController;
import com.library.mariela.productservice.dtos.ProductDto;
import com.library.mariela.productservice.entities.Product;
import com.library.mariela.productservice.models.HistoricalPurchase;
import com.library.mariela.productservice.services.IProductService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
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

    @CircuitBreaker(name = "allCB", fallbackMethod = "fallbackGetAll")
    @GetMapping("/all/{productId}")
    public ResponseEntity<?> getAll(@PathVariable Long productId) {
        Map<String, Object> results = this.commonService.getProductsWithStocksAndPurchases(productId);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @CircuitBreaker(name = "purchasesCB", fallbackMethod = "fallbackSavePurchase")
    @PostMapping("/savePurchase/{productId}")
    public ResponseEntity<?> savePurchase(@PathVariable Long productId, @RequestBody int minimum, @RequestBody HistoricalPurchase purchase) {
        if (this.commonService.findById(productId).isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(this.commonService.savePurchase(productId, minimum, purchase));
    }

    private ResponseEntity<?> fallbackSavePurchase(@PathVariable Long productId, @RequestBody HistoricalPurchase purchase, RuntimeException e){
        return new ResponseEntity<>("No pudo añadirse la compra.", HttpStatus.OK);
    }

    private ResponseEntity<?> fallbackGetAll(@PathVariable Long productId, @RequestBody HistoricalPurchase purchase, RuntimeException e){
        return new ResponseEntity<>("Este producto no tiene disponible el stock y las compras.", HttpStatus.OK);
    }
}
