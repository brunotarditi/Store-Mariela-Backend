package com.library.mariela.orderservice.orderservice.clients;

import com.library.mariela.orderservice.orderservice.dtos.OrderDetailDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name = "orders-details-service")
public interface IOrderFeignClient {

    @GetMapping("/api/ordersDetails/byOrder/{orderId}")
    List<OrderDetailDto> getOrderDetailsByOrderId(@PathVariable Long orderId);
}
