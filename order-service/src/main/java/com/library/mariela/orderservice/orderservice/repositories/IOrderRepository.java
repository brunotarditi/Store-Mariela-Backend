package com.library.mariela.orderservice.orderservice.repositories;

import com.library.mariela.orderservice.orderservice.entities.Order;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends PagingAndSortingRepository<Order, Long> {
}
