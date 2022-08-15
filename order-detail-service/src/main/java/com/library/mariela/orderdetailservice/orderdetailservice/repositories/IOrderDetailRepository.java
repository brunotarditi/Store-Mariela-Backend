package com.library.mariela.orderdetailservice.orderdetailservice.repositories;

import com.library.mariela.orderdetailservice.orderdetailservice.entities.OrderDetail;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderDetailRepository extends PagingAndSortingRepository<OrderDetail, Long> {
}
