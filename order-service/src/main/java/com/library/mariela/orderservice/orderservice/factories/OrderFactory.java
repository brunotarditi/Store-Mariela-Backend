package com.library.mariela.orderservice.orderservice.factories;

import com.library.commonsservice.factory.IFactory;
import com.library.commonsservice.mapper.ModelMapperConfig;
import com.library.mariela.orderservice.orderservice.dtos.OrderDto;
import com.library.mariela.orderservice.orderservice.entities.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderFactory extends ModelMapperConfig implements IFactory<Order, OrderDto> {
    @Override
    public Order createEntity(OrderDto orderDto) {
        return getModelMapper().map(orderDto, Order.class);
    }

    @Override
    public OrderDto createDto(Order order) {
        return getModelMapper().map(order, OrderDto.class);
    }
}
