package com.library.mariela.productservice.clients;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "historical-purchase-service")
public interface IHistoricalPurchaseFeignClient {
}
