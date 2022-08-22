package com.library.brandservice.controllers;

import com.library.brandservice.dtos.BrandDto;
import com.library.brandservice.dtos.ProductDto;
import com.library.brandservice.entities.Brand;
import com.library.brandservice.services.IBrandService;
import com.library.commonsservice.controllers.CommonController;
import com.library.commonsservice.messages.Message;
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

    @GetMapping("/allEnabled")
    public ResponseEntity<?> getAllEnabled(){
        return new ResponseEntity<>(commonService.getBrandsEnabled(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{brandId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long brandId) {
        Optional<BrandDto> brandDto = this.commonService.findById(brandId);
        if (brandDto.isEmpty())
            return new ResponseEntity<>(new Message("Marca no encontrada"), HttpStatus.NOT_FOUND);
        if (!brandDto.get().isEnabled())
            return new ResponseEntity<>(new Message("No existe la marca"), HttpStatus.BAD_REQUEST);
        List<ProductDto> productDtos = this.commonService.getProductByBrandId(brandId);
        if (!productDtos.isEmpty())
            return new ResponseEntity<>(new Message("Esta marca tiene productos asociados, no puede eliminarse"), HttpStatus.BAD_REQUEST);
        brandDto.get().setEnabled(false);
        this.commonService.save(brandDto.get());
        return new ResponseEntity<>(new Message("Marca eliminada con éxito"), HttpStatus.OK);
    }
}
