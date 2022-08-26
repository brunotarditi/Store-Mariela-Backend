package com.library.mariela.orderservice.orderservice.controllers;

import com.library.commonsservice.controllers.CommonController;
import com.library.commonsservice.messages.Message;
import com.library.mariela.orderservice.orderservice.dtos.OrderDetailDto;
import com.library.mariela.orderservice.orderservice.dtos.OrderDto;
import com.library.mariela.orderservice.orderservice.entities.Order;
import com.library.mariela.orderservice.orderservice.services.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody OrderDto orderDto) {
        Optional<OrderDto> orderDtoOptional = commonService.findById(id);
        if (orderDtoOptional.isEmpty())
            return new ResponseEntity<>("Pedido no encontrado.", HttpStatus.NOT_FOUND);
        orderDtoOptional.get().setStatus(orderDto.getStatus());
        orderDtoOptional.get().setPaymentMethod(orderDto.getPaymentMethod());
        return new ResponseEntity<>(commonService.save(orderDtoOptional.get()), HttpStatus.OK);
    }

    @GetMapping("/all/{orderId}")
    public ResponseEntity<?> getAll(@PathVariable Long orderId) {
        Map<String, Object> results = this.commonService.getOrderWithOrderDetails(orderId);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @GetMapping("/allEnabledWithDetails")
    public ResponseEntity<?> getAllOrdersActiveWithDetails(){
        Map<String, Object> results = this.commonService.getAllOrdersWithDetails();
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @PostMapping("/saveOrderDetail/{orderId}")
    public ResponseEntity<?> saveOrderDetail(@PathVariable Long orderId, @RequestBody OrderDetailDto orderDetailDto) throws Exception {
        if (this.commonService.findById(orderId).isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(this.commonService.saveOrderDetail(orderId, orderDetailDto));
    }

    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long orderId){
        Optional<OrderDto> orderDto = this.commonService.findById(orderId);
        if (orderDto.isEmpty())
            return new ResponseEntity<>(new Message("Pedido no encontrado"), HttpStatus.NOT_FOUND);
        if (!orderDto.get().isEnabled())
            return new ResponseEntity<>(new Message("No existe el pedido"), HttpStatus.BAD_REQUEST);
        List<OrderDetailDto> detailDto = this.commonService.getOrderDetailsByOrderId(orderId);
        if (!detailDto.isEmpty())
            return new ResponseEntity<>(new Message("Este pedido tiene detalles, no puede eliminarse"), HttpStatus.BAD_REQUEST);
        orderDto.get().setEnabled(false);
        this.commonService.save(orderDto.get());
        return new ResponseEntity<>(new Message("Pedido eliminado"), HttpStatus.OK);
    }
}
