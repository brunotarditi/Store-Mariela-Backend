package com.library.mariela.orderservice.orderservice.controllers;

import com.library.commonsservice.controllers.CommonController;
import com.library.mariela.orderservice.orderservice.dtos.OrderDto;
import com.library.mariela.orderservice.orderservice.entities.Order;
import com.library.mariela.orderservice.orderservice.enums.Status;
import com.library.mariela.orderservice.orderservice.services.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController extends CommonController<Order, OrderDto, IOrderService> {

    @Autowired
    public OrderController(IOrderService commonService) {
        super(commonService);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestParam OrderDto orderDto) {
        Optional<OrderDto> orderDtoOptional = commonService.findById(id);
        if (orderDtoOptional.isEmpty())
            return new ResponseEntity<>("Pedido no encontrado.", HttpStatus.NOT_FOUND);
        orderDtoOptional.get().setStatus(orderDto.getStatus());
        return new ResponseEntity<>(commonService.save(orderDtoOptional.get()), HttpStatus.OK);
    }

    @GetMapping("/all/{orderId}")
    public ResponseEntity<?> getAll(@PathVariable Long orderId) {
        Map<String, Object> results = this.commonService.getOrderWithOrderDetails(orderId);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @GetMapping("/status")
    public ResponseEntity<?> getStatus(){
        List<Status> statusList = Arrays.asList(Status.values());
        return new ResponseEntity<>(statusList, HttpStatus.OK);
    }

}
