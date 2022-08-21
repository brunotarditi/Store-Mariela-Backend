package com.library.mariela.orderservice.orderservice.repositories;

import com.library.mariela.orderservice.orderservice.entities.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepository extends PagingAndSortingRepository<Order, Long> {
    @Query(value = "SELECT o FROM Order o WHERE o.isEnabled = true")
    List<Order> findOrderEnabled();
}
