package com.library.mariela.productservice.controllers;

import com.library.commonsservice.controllers.CommonController;
import com.library.mariela.productservice.dtos.ProductDto;
import com.library.mariela.productservice.dtos.PurchaseStockControlDto;
import com.library.mariela.productservice.entities.Product;
import com.library.mariela.productservice.dtos.HistoricalPurchaseDto;
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
    public ResponseEntity<?> update(@Valid @RequestBody ProductDto productDto, BindingResult result, @PathVariable Long id) {
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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        Optional<ProductDto> productDto = commonService.findById(id);
        if (productDto.isEmpty())
            return new ResponseEntity<>("Producto no encontrado.", HttpStatus.NOT_FOUND);
        productDto.get().setDelete(true);

        return new ResponseEntity<>(commonService.save(productDto.get()), HttpStatus.OK);
    }

    @GetMapping("/byBrand/{brandId}")
    public ResponseEntity<?> getProductsByBrandId(@PathVariable Long brandId) {
        List<ProductDto> products = this.commonService.getProductBrandById(brandId);
        return ResponseEntity.ok(products);
    }

    @CircuitBreaker(name = "allCB", fallbackMethod = "fallbackGetAll")
    @GetMapping("/withStockAndPurchases/{productId}")
    public ResponseEntity<?> getProductWithStockAndPurchases(@PathVariable Long productId) {
        Map<String, Object> results = this.commonService.getProductWithStockAndPurchases(productId);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    private ResponseEntity<?> fallbackGetAll(@PathVariable Long productId, RuntimeException e) {
        return new ResponseEntity<>("Este producto no tiene disponible el stock y las compras.", HttpStatus.OK);
    }

    @CircuitBreaker(name = "purchasesCB", fallbackMethod = "fallbackSavePurchase")
    @PostMapping("/savePurchase/{productId}")
    public ResponseEntity<?> savePurchase(@PathVariable Long productId, @RequestBody PurchaseStockControlDto purchaseStock) {
        if (this.commonService.findById(productId).isEmpty())
            return ResponseEntity.notFound().build();
        return new ResponseEntity<>(this.commonService.savePurchase(productId, purchaseStock), HttpStatus.OK);
    }

    private ResponseEntity<?> fallbackSavePurchase(@PathVariable Long productId, @RequestBody PurchaseStockControlDto purchase, RuntimeException e) {
        return new ResponseEntity<>("No pudo añadirse la compra.", HttpStatus.OK);
    }

    @GetMapping("/withStock")
    public ResponseEntity<?> getProductsWithStock() {
        Map<String, Object> results = this.commonService.getAllProductsWithStocks();
        return new ResponseEntity<>(results, HttpStatus.OK);
    }
}
