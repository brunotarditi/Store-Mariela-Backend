package com.library.commonsservice.controllers;

import com.library.commonsservice.services.ICommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(this.commonService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "5") int size,
                                         @RequestParam(defaultValue = "name") String order,
                                         @RequestParam(defaultValue = "true") boolean asc) {
        Page<TDto> tDtos = this.commonService.findAll(PageRequest.of(page, size, Sort.by(order)));
        if (!asc)
            tDtos = this.commonService.findAll(PageRequest.of(page, size, Sort.by(order).descending()));
        return new ResponseEntity<>(tDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDtoById(@PathVariable Long id) {
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
        result.getFieldErrors().forEach(err -> errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
