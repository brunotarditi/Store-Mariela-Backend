package com.library.commonsservice.controllers;

import com.library.commonsservice.services.ICommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class CommonController<E, S extends ICommonService<E>> {
    protected final S commonService;

    @Autowired
    public CommonController(S commonService) {
        this.commonService = commonService;
    }
    @GetMapping("/all")
    public ResponseEntity<?> getProducts(){
        return new ResponseEntity<>(this.commonService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id){
        return new ResponseEntity<>(this.commonService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody E entity){
        return new ResponseEntity<>(this.commonService.save(entity), HttpStatus.OK);
    }
}
