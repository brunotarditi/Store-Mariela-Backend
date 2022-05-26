package com.library.mariela.historicalpurchasesservice.controllers;

import com.library.commonsservice.controllers.CommonController;
import com.library.mariela.historicalpurchasesservice.dtos.HistoricalPurchaseDto;
import com.library.mariela.historicalpurchasesservice.entities.HistoricalPurchase;
import com.library.mariela.historicalpurchasesservice.services.IHistoricalPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/purchases")
public class HistoricalPurchaseController extends CommonController<HistoricalPurchase, HistoricalPurchaseDto, IHistoricalPurchaseService> {

    @Autowired
    public HistoricalPurchaseController(IHistoricalPurchaseService commonService) {
        super(commonService);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody HistoricalPurchaseDto historicalPurchaseDto, BindingResult result, @PathVariable Long id){
        if (result.hasErrors())
            return this.validate(result);
        Optional<HistoricalPurchaseDto> purchaseDtoOptional = commonService.findById(id);
        if (purchaseDtoOptional.isEmpty())
            return new ResponseEntity<>("Compra no encontrada.", HttpStatus.NOT_FOUND);
        purchaseDtoOptional.get().setQuantity(historicalPurchaseDto.getQuantity());
        purchaseDtoOptional.get().setCostPrice(historicalPurchaseDto.getCostPrice());
        purchaseDtoOptional.get().setCreateAt(purchaseDtoOptional.get().getCreateAt());
        purchaseDtoOptional.get().setUpdateAt(new Date());
        purchaseDtoOptional.get().setProductId(historicalPurchaseDto.getProductId());

        return new ResponseEntity<>(commonService.save(purchaseDtoOptional.get()), HttpStatus.OK);
    }

    @GetMapping("/byProduct/{productId}")
    public ResponseEntity<?> getHistoricalPurchaseByProductId(@PathVariable Long productId) {
        List<HistoricalPurchaseDto> products = this.commonService.getHistoricalPurchaseByProductId(productId);
        return ResponseEntity.ok(products);
    }
}
