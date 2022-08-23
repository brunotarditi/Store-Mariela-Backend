package com.library.mariela.orderdetailservice.orderdetailservice.services;

import com.library.commonsservice.services.CommonService;
import com.library.mariela.orderdetailservice.orderdetailservice.clients.IStockControlFeignClient;
import com.library.mariela.orderdetailservice.orderdetailservice.dtos.OrderDetailDto;
import com.library.mariela.orderdetailservice.orderdetailservice.dtos.StockControlDto;
import com.library.mariela.orderdetailservice.orderdetailservice.entities.OrderDetail;
import com.library.mariela.orderdetailservice.orderdetailservice.factories.OrderDetailFactory;
import com.library.mariela.orderdetailservice.orderdetailservice.repositories.IOrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderDetailService extends CommonService<OrderDetail, IOrderDetailRepository, OrderDetailDto> implements IOrderDetailService {

    private final OrderDetailFactory orderDetailFactory;
    private final IStockControlFeignClient iStockControlFeignClient;
    @Autowired
    public OrderDetailService(IOrderDetailRepository repository, OrderDetailFactory orderDetailFactory, IStockControlFeignClient iStockControlFeignClient) {
        super(repository, orderDetailFactory);
        this.orderDetailFactory = orderDetailFactory;
        this.iStockControlFeignClient = iStockControlFeignClient;
    }

    @Override
    public List<OrderDetailDto> getOrderDetailByIdOrder(Long orderId) {
        List<OrderDetail> orderDetails = this.repository.findOrderDetailByIdOrder(orderId);
        return orderDetails
                .stream()
                .map(iFactory::createDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDetailDto> getAllOrdersDetailInactive() {
        List<OrderDetail> orderDetails = this.repository.findOrderDetailInactive();
        return orderDetails
                .stream()
                .map(iFactory::createDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDetail> saveAll(List<OrderDetailDto> orderDetailDtos) {
        List<OrderDetail> brands = this.orderDetailFactory.createEntitiesList(orderDetailDtos);
        return (List<OrderDetail>) this.repository.saveAll(brands);
    }

    @Override
    public OrderDetail addItemToOrderDetail(Long productId, OrderDetailDto orderDetailDto) {
        Optional<StockControlDto> stockControlDto = iStockControlFeignClient.getStockControlByProductId(productId);
        if (stockControlDto.isEmpty())
            return null;
        stockControlDto.get().setCurrent(stockControlDto.get().getCurrent() - orderDetailDto.getQuantity());
        StockControlDto stockControlDtoUpdate = iStockControlFeignClient.save(stockControlDto.get());
        if (stockControlDtoUpdate == null)
            return null;
        OrderDetail orderDetail = iFactory.createEntity(orderDetailDto);
        return repository.save(orderDetail);
    }

    @Override
    public OrderDetail deleteItemToOrderDetail(Long id) {
        Optional<OrderDetailDto> orderDetailDto = findById(id);
        if (orderDetailDto.isEmpty())
            return null;
        Optional<StockControlDto> stockControlDto = iStockControlFeignClient.getStockControlByProductId(orderDetailDto.get().getIdProduct());
        if (stockControlDto.isEmpty())
            return null;
        stockControlDto.get().setCurrent(stockControlDto.get().getCurrent() + orderDetailDto.get().getQuantity());
        StockControlDto stockControlDtoUpdate = iStockControlFeignClient.save(stockControlDto.get());
        if (stockControlDtoUpdate == null)
            return null;
        OrderDetail orderDetail = iFactory.createEntity(orderDetailDto.get());
        orderDetail.setEnabled(false);
        return repository.save(orderDetail);
    }

}
