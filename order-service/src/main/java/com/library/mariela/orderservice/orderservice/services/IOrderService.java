package com.library.mariela.orderservice.orderservice.services;

import com.library.commonsservice.services.ICommonService;
import com.library.mariela.orderservice.orderservice.dtos.OrderDetailDto;
import com.library.mariela.orderservice.orderservice.dtos.OrderDto;
import com.library.mariela.orderservice.orderservice.entities.Order;

import java.util.List;
import java.util.Map;

public interface IOrderService extends ICommonService<Order, OrderDto> {
    Map<String, Object> getOrderWithOrderDetails(Long orderId);
    Map<String, Object> getAllOrdersWithDetails();
    OrderDetailDto saveOrderDetail(Long orderId, OrderDetailDto orderDetailDto);
    List<OrderDetailDto> getOrderDetailsByOrderId(Long orderId);
}
