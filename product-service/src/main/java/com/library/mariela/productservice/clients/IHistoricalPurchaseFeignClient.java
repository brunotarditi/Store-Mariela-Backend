package com.library.mariela.productservice.clients;

import com.library.mariela.productservice.dtos.HistoricalPurchaseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "historical-purchase-service")
public interface IHistoricalPurchaseFeignClient {

    @GetMapping("/api/purchases/byProduct/{productId}")
    List<HistoricalPurchaseDto> getHistoricalPurchaseByProductId(@PathVariable Long productId);

    @PostMapping("/api/purchases/save")
    HistoricalPurchaseDto save(@RequestBody HistoricalPurchaseDto stockControl);
}
