package com.library.mariela.orderdetailservice.orderdetailservice.controllers;

import com.library.commonsservice.controllers.CommonController;
import com.library.mariela.orderdetailservice.orderdetailservice.dtos.OrderDetailDto;
import com.library.mariela.orderdetailservice.orderdetailservice.entities.OrderDetail;
import com.library.mariela.orderdetailservice.orderdetailservice.services.IOrderDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/ordersDetails")
public class OrderDetailController extends CommonController<OrderDetail, OrderDetailDto, IOrderDetailService> {

    public OrderDetailController(IOrderDetailService commonService) {
        super(commonService);
    }

    @GetMapping("/byOrder/{orderId}")
    public ResponseEntity<?> getOrderDetailsByOrderId(@PathVariable Long orderId) {
        List<OrderDetailDto> orderDetailDtos = this.commonService.getOrderDetailByIdOrder(orderId);
        return ResponseEntity.ok(orderDetailDtos);
    }
}
