package com.library.mariela.orderdetailservice.orderdetailservice.factories;

import com.library.commonsservice.factory.IFactory;
import com.library.commonsservice.mapper.ModelMapperConfig;
import com.library.mariela.orderdetailservice.orderdetailservice.dtos.OrderDetailDto;
import com.library.mariela.orderdetailservice.orderdetailservice.entities.OrderDetail;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderDetailFactory extends ModelMapperConfig implements IFactory<OrderDetail, OrderDetailDto> {
    @Override
    public OrderDetail createEntity(OrderDetailDto orderDetailDto) {
        return getModelMapper().map(orderDetailDto, OrderDetail.class);
    }

    @Override
    public OrderDetailDto createDto(OrderDetail orderDetail) {
        return getModelMapper().map(orderDetail, OrderDetailDto.class);
    }

    public List<OrderDetail> createEntitiesList(List<OrderDetailDto> orderDetailDtos){
        List<OrderDetail> orderDetails = new ArrayList<>();
        orderDetailDtos.forEach(orderDetailDto -> {
            orderDetails.add(createEntity(orderDetailDto));
        });
        return orderDetails;
    }
}
