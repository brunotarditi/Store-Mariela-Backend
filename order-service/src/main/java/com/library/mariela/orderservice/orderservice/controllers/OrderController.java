package com.library.mariela.orderservice.orderservice.controllers;

import com.library.commonsservice.controllers.CommonController;
import com.library.mariela.orderservice.orderservice.dtos.OrderDto;
import com.library.mariela.orderservice.orderservice.entities.Order;
import com.library.mariela.orderservice.orderservice.enums.Status;
import com.library.mariela.orderservice.orderservice.services.IOrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController extends CommonController<Order, OrderDto, IOrderService> {

    @Autowired
    public OrderController(IOrderService commonService) {
        super(commonService);
    }

    @PutMapping("/id/{id}/status/{status}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestParam Status status) {
        Optional<OrderDto> orderDto = commonService.findById(id);
        if (orderDto.isEmpty())
            return new ResponseEntity<>("Pedido no encontrado.", HttpStatus.NOT_FOUND);
        switch (status){
            case DENY:
                orderDto.get().setStatus(Status.DENY);
                break;
            case OK:
                orderDto.get().setStatus(Status.OK);
                break;
            case PREPARATION:
                orderDto.get().setStatus(Status.PREPARATION);
                break;
            case CANCELLED:
                orderDto.get().setStatus(Status.CANCELLED);
                break;
            case DELIVERED:
                orderDto.get().setStatus(Status.DELIVERED);
                break;
        }
        return new ResponseEntity<>(commonService.save(orderDto.get()), HttpStatus.OK);
    }


}
