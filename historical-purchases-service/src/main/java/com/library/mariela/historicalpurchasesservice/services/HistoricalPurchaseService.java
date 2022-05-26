package com.library.mariela.historicalpurchasesservice.services;

import com.library.commonsservice.factory.IFactory;
import com.library.commonsservice.services.CommonService;
import com.library.mariela.historicalpurchasesservice.dtos.HistoricalPurchaseDto;
import com.library.mariela.historicalpurchasesservice.entities.HistoricalPurchase;
import com.library.mariela.historicalpurchasesservice.repositories.IHistoricalPurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoricalPurchaseService extends CommonService<HistoricalPurchase, IHistoricalPurchaseRepository, HistoricalPurchaseDto> implements IHistoricalPurchaseService {

    public HistoricalPurchaseService(IHistoricalPurchaseRepository repository, IFactory<HistoricalPurchase, HistoricalPurchaseDto> iFactory) {
        super(repository, iFactory);
    }

    @Override
    public List<HistoricalPurchaseDto> getHistoricalPurchaseByProductId(Long id) {
        List<HistoricalPurchase> historicalPurchases = this.repository.findHistoricalPurchaseByProductId(id);
        return historicalPurchases
                .stream()
                .map(iFactory::createDto)
                .collect(Collectors.toList());
    }
}
