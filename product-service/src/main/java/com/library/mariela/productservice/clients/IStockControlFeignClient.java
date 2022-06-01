package com.library.mariela.productservice.clients;

import com.library.mariela.productservice.models.StockControl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "stock-control-service")
public interface IStockControlFeignClient {

    @GetMapping("/api/stocks/byProduct/{productId}")
    Optional<StockControl> getStockControlByProductId(@PathVariable Long productId);

    @PostMapping("/api/stocks/save")
    StockControl save(@RequestBody StockControl stockControl);
}
