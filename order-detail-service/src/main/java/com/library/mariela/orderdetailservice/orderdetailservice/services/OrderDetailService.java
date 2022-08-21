package com.library.mariela.orderdetailservice.orderdetailservice.services;

import com.library.commonsservice.factory.IFactory;
import com.library.commonsservice.services.CommonService;
import com.library.mariela.orderdetailservice.orderdetailservice.dtos.OrderDetailDto;
import com.library.mariela.orderdetailservice.orderdetailservice.entities.OrderDetail;
import com.library.mariela.orderdetailservice.orderdetailservice.repositories.IOrderDetailRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDetailService extends CommonService<OrderDetail, IOrderDetailRepository, OrderDetailDto> implements IOrderDetailService {

    public OrderDetailService(IOrderDetailRepository repository, IFactory<OrderDetail, OrderDetailDto> iFactory) {
        super(repository, iFactory);
    }

    @Override
    public List<OrderDetailDto> getOrderDetailByIdOrder(Long orderId) {
        List<OrderDetail> orderDetails = this.repository.findOrderDetailByIdOrder(orderId);
        return orderDetails
                .stream()
                .map(iFactory::createDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDetailDto> getAllOrdersDetailInactive() {
        List<OrderDetail> orderDetails = this.repository.findOrderDetailInactive();
        return orderDetails
                .stream()
                .map(iFactory::createDto)
                .collect(Collectors.toList());
    }

}
