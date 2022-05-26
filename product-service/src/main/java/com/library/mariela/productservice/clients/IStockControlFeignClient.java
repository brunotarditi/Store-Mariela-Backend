package com.library.mariela.productservice.clients;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "stock-control-service")
public interface IStockControlFeignClient {
}
