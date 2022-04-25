package com.library.categoryservice.controllers;

import com.library.categoryservice.entities.Category;
import com.library.categoryservice.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCategories(){
        return new ResponseEntity<>(this.categoryService.findAllCategories(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneCategory(@PathVariable Long id){
        return new ResponseEntity<>(this.categoryService.findByIdCategory(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Category category){
        return new ResponseEntity<>(this.categoryService.save(category), HttpStatus.OK);
    }
}
