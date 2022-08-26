package com.library.mariela.orderdetailservice.orderdetailservice.controllers;

import com.library.commonsservice.controllers.CommonController;
import com.library.commonsservice.messages.Message;
import com.library.mariela.orderdetailservice.orderdetailservice.dtos.OrderDetailDto;
import com.library.mariela.orderdetailservice.orderdetailservice.entities.OrderDetail;
import com.library.mariela.orderdetailservice.orderdetailservice.services.IOrderDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/allEnabled")
    public ResponseEntity<?> getOrderDetailsActive() {
        List<OrderDetailDto> orderDetailDtos = this.commonService.getAllOrdersDetailInactive();
        return ResponseEntity.ok(orderDetailDtos);
    }

    @PostMapping("/save/all")
    public ResponseEntity<?> saveAll(@Valid @RequestBody List<OrderDetailDto> orderDetailDtos, BindingResult result) {
        return new ResponseEntity<>(commonService.saveAll(orderDetailDtos), HttpStatus.OK);
    }

    @PostMapping("/addToCart/{productId}")
    public ResponseEntity<?> addItemToOrderDetail(@PathVariable Long productId, @RequestBody OrderDetailDto orderDetailDto) {
        OrderDetail orderDetail = commonService.addItemToOrderDetail(productId, orderDetailDto);
        if (orderDetail == null)
            return new ResponseEntity<>(new Message("No pudo añadirse al carrito"), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(orderDetail, HttpStatus.OK);
    }

    @DeleteMapping("/deleteToCart/{id}")
    public ResponseEntity<?> deleteItemToOrderDetail(@PathVariable Long id) {
        OrderDetail orderDetail = commonService.deleteItemToOrderDetail(id);
        if (orderDetail == null)
            return new ResponseEntity<>(new Message("No pudo eliminarse del carrito"), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(new Message("Se quitó del carrito"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{orderDetailId}")
    public ResponseEntity<?> deleteOrderDetail(@PathVariable Long orderDetailId) {
        Optional<OrderDetailDto> orderDto = this.commonService.findById(orderDetailId);
        if (orderDto.isEmpty())
            return new ResponseEntity<>(new Message("Detalle no encontrado"), HttpStatus.NOT_FOUND);
        if (orderDto.get().isEnabled())
            return new ResponseEntity<>(new Message("No existe el detalle"), HttpStatus.BAD_REQUEST);
        orderDto.get().setEnabled(false);
        this.commonService.save(orderDto.get());
        return new ResponseEntity<>(new Message("Detalle eliminado"), HttpStatus.OK);
    }
}
