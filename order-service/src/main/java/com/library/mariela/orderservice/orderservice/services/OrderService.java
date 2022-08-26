package com.library.mariela.orderservice.orderservice.services;

import com.library.commonsservice.services.CommonService;
import com.library.mariela.orderservice.orderservice.clients.IOrderFeignClient;
import com.library.mariela.orderservice.orderservice.dtos.OrderDetailDto;
import com.library.mariela.orderservice.orderservice.dtos.OrderDto;
import com.library.mariela.orderservice.orderservice.entities.Order;
import com.library.mariela.orderservice.orderservice.factories.OrderFactory;
import com.library.mariela.orderservice.orderservice.repositories.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService extends CommonService<Order, IOrderRepository, OrderDto> implements IOrderService {

    private final IOrderFeignClient orderFeignClient;

    @Autowired
    public OrderService(IOrderRepository repository, OrderFactory orderFactory, IOrderFeignClient orderFeignClient) {
        super(repository, orderFactory);
        this.orderFeignClient = orderFeignClient;
    }

    @Override
    public Map<String, Object> getOrderWithOrderDetails(Long orderId) {
        Map<String, Object> results = new HashMap<>();
        Optional<OrderDto> orderDto = this.findById(orderId);
        if (orderDto.isEmpty()){
            results.put("Message", "No existe el pedido.");
            return results;
        }
        results.put("Order", orderDto);
        List<OrderDetailDto> orderDetailDtos = orderFeignClient.getOrderDetailsByOrderId(orderId);
        if (orderDetailDtos.isEmpty()){
            results.put("Message", "No existe detalles para el pedido.");
            return results;
        }else {
            results.put("Details", orderDetailDtos);
        }
        return results;
    }

    @Override
    public Map<String, Object> getAllOrdersWithDetails() {
        Map<String, Object> results = new HashMap<>();
        List<Order> ordersIsEnabled = repository.findOrderEnabled();
        List<OrderDetailDto> orderDetailDtos = orderFeignClient.getOrderDetailsAvailable();
        List<OrderDto> orderDtosAvailable = ordersIsEnabled
                .stream()
                .map(iFactory::createDto)
                .collect(Collectors.toList());
        for (OrderDto orderDto : orderDtosAvailable) {
            double total = 0;
            for (OrderDetailDto orderDetailDto : orderDetailDtos){
                if (orderDto.getId().equals(orderDetailDto.getIdOrder())){
                    total += orderDetailDto.getQuantity() * orderDetailDto.getSubtotal();
                    orderDto.getOrderDetailDtos().add(orderDetailDto);
                    orderDto.setTotal(total);
                }
            }
        }
        results.put("Orders", orderDtosAvailable);
        return results;
    }

    @Override
    public OrderDetailDto saveOrderDetail(Long orderId, OrderDetailDto orderDetailDto){
        orderDetailDto.setIdOrder(orderId);
        return this.orderFeignClient.save(orderDetailDto);
    }

    @Override
    public List<OrderDetailDto> getOrderDetailsByOrderId(Long orderId) {
        return orderFeignClient.getOrderDetailsByOrderId(orderId);
    }
}
