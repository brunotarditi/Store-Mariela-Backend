package com.library.categoryservice.controllers;

import com.library.categoryservice.entities.Category;
import com.library.categoryservice.services.ICategoryService;
import com.library.commonsservice.controllers.CommonController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController extends CommonController<Category, ICategoryService> {

    public CategoryController(ICategoryService iCategoryService) {
        super(iCategoryService);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> save(@RequestBody Category category, @PathVariable Long id){
        Optional<Category> categoryOptional = commonService.findById(id);
        if (categoryOptional.isEmpty())
            return new ResponseEntity<>("Categoría no encontrada.", HttpStatus.NOT_FOUND);
        categoryOptional.get().setName(category.getName());

        return new ResponseEntity<>(commonService.save(categoryOptional.get()), HttpStatus.OK);
    }
}
