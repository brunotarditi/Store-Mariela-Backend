package com.library.stockcontrolservice.repositories;

import com.library.stockcontrolservice.entities.StockControl;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStockControlRepository extends PagingAndSortingRepository<StockControl, Long> {

    List<StockControl> findStockControlByProductId(Long productId);
}
