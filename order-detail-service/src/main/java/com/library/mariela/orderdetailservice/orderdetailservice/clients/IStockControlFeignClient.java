package com.library.mariela.orderdetailservice.orderdetailservice.clients;

import com.library.mariela.orderdetailservice.orderdetailservice.dtos.StockControlDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@FeignClient(name = "stock-control-service")
public interface IStockControlFeignClient {

    @GetMapping("/api/stocks/byProduct/{productId}")
    Optional<StockControlDto> getStockControlByProductId(@PathVariable Long productId);

    @PostMapping("/api/stocks/save")
    StockControlDto save(@RequestBody StockControlDto stockControl);
}
