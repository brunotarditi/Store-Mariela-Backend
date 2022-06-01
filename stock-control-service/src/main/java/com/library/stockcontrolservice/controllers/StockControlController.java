package com.library.stockcontrolservice.controllers;

import com.library.commonsservice.controllers.CommonController;
import com.library.stockcontrolservice.dtos.StockControlDto;
import com.library.stockcontrolservice.entities.StockControl;
import com.library.stockcontrolservice.services.IStockControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/stocks")
public class StockControlController extends CommonController<StockControl, StockControlDto, IStockControlService> {

    @Autowired
    public StockControlController(IStockControlService iStockControlService) {
        super(iStockControlService);
    }

    @GetMapping("/byProduct/{productId}")
    public ResponseEntity<?> getStockControlByProductId(@PathVariable Long productId) {
        Optional<StockControlDto> products = this.commonService.getStockControlByProductId(productId);
        return ResponseEntity.ok(products);
    }
}
