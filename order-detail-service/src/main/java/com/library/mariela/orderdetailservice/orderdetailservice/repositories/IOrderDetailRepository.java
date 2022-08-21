package com.library.mariela.orderdetailservice.orderdetailservice.repositories;

import com.library.mariela.orderdetailservice.orderdetailservice.entities.OrderDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderDetailRepository extends PagingAndSortingRepository<OrderDetail, Long> {
    List<OrderDetail> findOrderDetailByIdOrder(Long orderId);
    @Query(value = "SELECT od FROM OrderDetail od WHERE od.isInactive = true")
    List<OrderDetail> findOrderDetailInactive();
}
