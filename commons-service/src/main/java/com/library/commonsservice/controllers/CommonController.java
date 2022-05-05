package com.library.commonsservice.controllers;

import com.library.commonsservice.services.ICommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

public class CommonController<E, TDto, S extends ICommonService<E, TDto>> {
    protected final S commonService;

    @Autowired
    public CommonController(S commonService) {
        this.commonService = commonService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getProducts() {
        return new ResponseEntity<>(this.commonService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        return new ResponseEntity<>(this.commonService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody TDto tDto, BindingResult result) {
        if (result.hasErrors())
            return this.validate(result);
        return new ResponseEntity<>(this.commonService.save(tDto), HttpStatus.OK);
    }

    protected ResponseEntity<?> validate(BindingResult result) {
        Map<String, Object> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
