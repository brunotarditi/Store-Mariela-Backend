package com.library.categoryservice.controllers;

import com.library.categoryservice.dtos.CategoryDto;
import com.library.categoryservice.entities.Category;
import com.library.categoryservice.services.ICategoryService;
import com.library.commonsservice.controllers.CommonController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
}
