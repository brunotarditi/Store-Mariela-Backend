package com.library.mariela.orderservice.orderservice.services;

import com.library.commonsservice.services.ICommonService;
import com.library.mariela.orderservice.orderservice.dtos.OrderDto;
import com.library.mariela.orderservice.orderservice.entities.Order;

import java.util.Map;

public interface IOrderService extends ICommonService<Order, OrderDto> {
    Map<String, Object> getOrderWithOrderDetails(Long orderId);
    Map<String, Object> getAllOrdersWithDetails();
    String deleteOrder(Long orderId);
}
