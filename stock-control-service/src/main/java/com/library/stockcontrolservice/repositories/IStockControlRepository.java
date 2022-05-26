package com.library.stockcontrolservice.repositories;

import com.library.stockcontrolservice.entities.StockControl;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStockControlRepository extends PagingAndSortingRepository<StockControl, Long> {
}
