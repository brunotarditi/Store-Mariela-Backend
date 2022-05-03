package com.library.brandservice.controllers;

import com.library.brandservice.entities.Brand;
import com.library.brandservice.services.IBrandService;
import com.library.commonsservice.controllers.CommonController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/brands")
public class BrandController extends CommonController<Brand, IBrandService> {

    public BrandController(IBrandService iBrandService) {
        super(iBrandService);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> save(@RequestBody Brand brand, @PathVariable Long id) {
        Optional<Brand> brandOptional = commonService.findById(id);
        if (brandOptional.isEmpty())
            return new ResponseEntity<>("Marca no encontrada.", HttpStatus.NOT_FOUND);
        brandOptional.get().setName(brand.getName());

        return new ResponseEntity<>(commonService.save(brandOptional.get()), HttpStatus.OK);
    }
}
