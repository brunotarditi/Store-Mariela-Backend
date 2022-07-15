package com.library.brandservice.controllers;

import com.library.brandservice.dtos.BrandDto;
import com.library.brandservice.entities.Brand;
import com.library.brandservice.services.IBrandService;
import com.library.commonsservice.controllers.CommonController;
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
@RequestMapping("/api/brands")
public class BrandController extends CommonController<Brand, BrandDto, IBrandService> {

    public BrandController(IBrandService iBrandService) {
        super(iBrandService);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@Valid @RequestBody BrandDto brandDto, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors())
            return this.validate(result);
        Optional<BrandDto> brandOptional = commonService.findById(id);
        if (brandOptional.isEmpty())
            return new ResponseEntity<>("Marca no encontrada.", HttpStatus.NOT_FOUND);
        brandOptional.get().setName(brandDto.getName());
        brandOptional.get().setCreateAt(brandOptional.get().getCreateAt());
        brandOptional.get().setUpdateAt(new Date());
        return new ResponseEntity<>(commonService.save(brandOptional.get()), HttpStatus.OK);
    }

    @PostMapping("/save/all")
    public ResponseEntity<?> saveAll(@Valid @RequestBody List<BrandDto> brandDtos, BindingResult result) {
        return new ResponseEntity<>(commonService.saveAll(brandDtos), HttpStatus.OK);
    }

    @GetMapping("/all/{brandId}")
    public ResponseEntity<?> getAll(@PathVariable Long brandId) {
        Map<String, Object> results = this.commonService.getBrandsWithProducts(brandId);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }
}
