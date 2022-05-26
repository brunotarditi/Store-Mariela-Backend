package com.library.mariela.historicalpurchasesservice.repositories;

import com.library.mariela.historicalpurchasesservice.entities.HistoricalPurchase;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IHistoricalPurchaseRepository extends PagingAndSortingRepository<HistoricalPurchase, Long> {

    List<HistoricalPurchase> findHistoricalPurchaseByProductId(Long productId);
}
