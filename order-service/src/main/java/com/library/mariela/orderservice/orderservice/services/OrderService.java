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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
        List<OrderDto> allProductsDtos = this.findAll();
        /*List<OrderDto> productsDtosAvailable = allProductsDtos
                .stream()
                .filter(productDto -> !productDto.isDelete())
                .collect(Collectors.toList());
        productsDtosAvailable
                .forEach(p -> stockControlFeignClient.getStockControlByProductId(p.getId())
                        .ifPresent(stockControl -> {
                            p.setStockControl(stockControl);
                            p.setPrice(stockControl.getListOfPrice() * ((float) stockControl.getPercent() / 100) + stockControl.getListOfPrice());
                        }));*/
        //results.put("Products", productsDtosAvailable);
        return results;
    }

    @Override
    public String deleteOrder(Long orderId){
        Optional<OrderDto> orderDto = this.findById(orderId);
        if (orderDto.isEmpty())
            return "Pedido no encontrado.";
        if (orderDto.get().isDelete())
            return "No existe el pedido.";
        List<OrderDetailDto> detailDto = orderFeignClient.getOrderDetailsByOrderId(orderId);
        if (!detailDto.isEmpty())
            return  "Este pedido cuenta tiene detalles, no puede eliminarse.";
        orderDto.get().setDelete(true);
        this.save(orderDto.get());
        return "Pedido eliminado con éxito.";
    }
}
