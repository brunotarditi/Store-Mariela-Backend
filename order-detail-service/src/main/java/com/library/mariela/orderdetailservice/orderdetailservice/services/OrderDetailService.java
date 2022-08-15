package com.library.mariela.orderdetailservice.orderdetailservice.services;

import com.library.commonsservice.factory.IFactory;
import com.library.commonsservice.services.CommonService;
import com.library.mariela.orderdetailservice.orderdetailservice.dtos.OrderDetailDto;
import com.library.mariela.orderdetailservice.orderdetailservice.entities.OrderDetail;
import com.library.mariela.orderdetailservice.orderdetailservice.repositories.IOrderDetailRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailService extends CommonService<OrderDetail, IOrderDetailRepository, OrderDetailDto> implements IOrderDetailService {

    public OrderDetailService(IOrderDetailRepository repository, IFactory<OrderDetail, OrderDetailDto> iFactory) {
        super(repository, iFactory);
    }
}
