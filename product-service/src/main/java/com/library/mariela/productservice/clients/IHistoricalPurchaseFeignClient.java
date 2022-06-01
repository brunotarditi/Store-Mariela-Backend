package com.library.mariela.productservice.clients;

import com.library.mariela.productservice.models.HistoricalPurchase;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "historical-purchase-service")
public interface IHistoricalPurchaseFeignClient {

    @GetMapping("/api/purchases/byProduct/{productId}")
    List<HistoricalPurchase> getHistoricalPurchaseByProductId(@PathVariable Long productId);

    @PostMapping("/api/purchases/save")
    HistoricalPurchase save(@RequestBody HistoricalPurchase stockControl);
}
