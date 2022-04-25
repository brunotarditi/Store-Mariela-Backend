package com.library.brandservice.controllers;

import com.library.brandservice.entities.Brand;
import com.library.brandservice.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/brands")
public class BrandController {
    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllBrands() {
        return new ResponseEntity<>(this.brandService.findAllBrands(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneBrand(@PathVariable Long id) {
        return new ResponseEntity<>(this.brandService.findByIdBrand(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Brand brand) {
        return new ResponseEntity<>(this.brandService.save(brand), HttpStatus.OK);
    }
}
