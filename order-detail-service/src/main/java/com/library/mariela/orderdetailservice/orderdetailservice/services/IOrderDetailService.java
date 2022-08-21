package com.library.mariela.orderdetailservice.orderdetailservice.services;

import com.library.commonsservice.services.ICommonService;
import com.library.mariela.orderdetailservice.orderdetailservice.dtos.OrderDetailDto;
import com.library.mariela.orderdetailservice.orderdetailservice.entities.OrderDetail;

import java.util.List;

public interface IOrderDetailService extends ICommonService<OrderDetail, OrderDetailDto> {
    List<OrderDetailDto> getOrderDetailByIdOrder(Long orderId);
    List<OrderDetailDto> getAllOrdersDetailInactive();
}
