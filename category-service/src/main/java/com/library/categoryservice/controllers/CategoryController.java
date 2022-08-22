package com.library.categoryservice.controllers;

import com.library.categoryservice.dtos.CategoryDto;
import com.library.categoryservice.dtos.ProductDto;
import com.library.categoryservice.entities.Category;
import com.library.categoryservice.services.ICategoryService;
import com.library.commonsservice.controllers.CommonController;
import com.library.commonsservice.messages.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController extends CommonController<Category, CategoryDto, ICategoryService> {

    public CategoryController(ICategoryService iCategoryService) {
        super(iCategoryService);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> save(@Valid @RequestBody CategoryDto CategoryDto, BindingResult result, @PathVariable Long id){
        if (result.hasErrors())
            return this.validate(result);
        Optional<CategoryDto> categoryOptional = commonService.findById(id);
        if (categoryOptional.isEmpty())
            return new ResponseEntity<>("Categoría no encontrada.", HttpStatus.NOT_FOUND);
        categoryOptional.get().setName(CategoryDto.getName());

        return new ResponseEntity<>(commonService.save(categoryOptional.get()), HttpStatus.OK);
    }

    @GetMapping("/all/{categoryId}")
    public ResponseEntity<?> getAll(@PathVariable Long categoryId) {
        Map<String, Object> results = this.commonService.getCategoriesWithProducts(categoryId);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @GetMapping("/allEnabled")
    public ResponseEntity<?> getAllEnabled(){
        return new ResponseEntity<>(commonService.getCategoriesEnabled(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long categoryId) {
        Optional<CategoryDto> categoryDto = this.commonService.findById(categoryId);
        if (categoryDto.isEmpty())
            return new ResponseEntity<>(new Message("Categoría no encontrada"), HttpStatus.NOT_FOUND);
        if (!categoryDto.get().isEnabled())
            return new ResponseEntity<>(new Message("No existe la categoría"), HttpStatus.BAD_REQUEST);
        List<ProductDto> productDtos = this.commonService.getProductByCategoryId(categoryId);
        if (!productDtos.isEmpty())
            return new ResponseEntity<>(new Message("Esta categoría tiene productos asociados, no puede eliminarse"), HttpStatus.BAD_REQUEST);
        categoryDto.get().setEnabled(false);
        this.commonService.save(categoryDto.get());
        return new ResponseEntity<>(new Message("Categoría eliminada con éxito"), HttpStatus.OK);
    }
}
