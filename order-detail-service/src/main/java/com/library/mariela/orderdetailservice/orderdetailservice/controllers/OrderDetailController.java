package com.library.mariela.orderdetailservice.orderdetailservice.controllers;

import com.library.commonsservice.controllers.CommonController;
import com.library.mariela.orderdetailservice.orderdetailservice.dtos.OrderDetailDto;
import com.library.mariela.orderdetailservice.orderdetailservice.entities.OrderDetail;
import com.library.mariela.orderdetailservice.orderdetailservice.services.IOrderDetailService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ordersDetails")
public class OrderDetailController extends CommonController<OrderDetail, OrderDetailDto, IOrderDetailService> {

    public OrderDetailController(IOrderDetailService commonService) {
        super(commonService);
    }
}
