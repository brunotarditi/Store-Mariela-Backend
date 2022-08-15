package com.library.mariela.orderservice.orderservice.services;

import com.library.commonsservice.factory.IFactory;
import com.library.commonsservice.services.CommonService;
import com.library.mariela.orderservice.orderservice.dtos.OrderDto;
import com.library.mariela.orderservice.orderservice.entities.Order;
import com.library.mariela.orderservice.orderservice.repositories.IOrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService extends CommonService<Order, IOrderRepository, OrderDto> implements IOrderService {

    public OrderService(IOrderRepository repository, IFactory<Order, OrderDto> iFactory) {
        super(repository, iFactory);
    }
}
