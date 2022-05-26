package com.library.mariela.historicalpurchasesservice.factories;

import com.library.commonsservice.factory.IFactory;
import com.library.commonsservice.mapper.ModelMapperConfig;
import com.library.mariela.historicalpurchasesservice.dtos.HistoricalPurchaseDto;
import com.library.mariela.historicalpurchasesservice.entities.HistoricalPurchase;
import org.springframework.stereotype.Component;

@Component
public class HistoricalPurchaseFactory extends ModelMapperConfig implements IFactory<HistoricalPurchase, HistoricalPurchaseDto> {

    @Override
    public HistoricalPurchase createEntity(HistoricalPurchaseDto historicalPurchaseDto) {
        return getModelMapper().map(historicalPurchaseDto, HistoricalPurchase.class);
    }

    @Override
    public HistoricalPurchaseDto createDto(HistoricalPurchase historicalPurchase) {
        return getModelMapper().map(historicalPurchase, HistoricalPurchaseDto.class);
    }
}
