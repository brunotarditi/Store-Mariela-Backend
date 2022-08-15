package com.library.mariela.orderservice.orderservice.services;

import com.library.commonsservice.services.ICommonService;
import com.library.mariela.orderservice.orderservice.dtos.OrderDto;
import com.library.mariela.orderservice.orderservice.entities.Order;

public interface IOrderService extends ICommonService<Order, OrderDto> {
}
