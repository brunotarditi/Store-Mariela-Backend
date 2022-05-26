package com.library.stockcontrolservice.factories;

import com.library.commonsservice.factory.IFactory;
import com.library.commonsservice.mapper.ModelMapperConfig;
import com.library.stockcontrolservice.dtos.StockControlDto;
import com.library.stockcontrolservice.entities.StockControl;
import org.springframework.stereotype.Component;

@Component
public class StockFactory extends ModelMapperConfig implements IFactory<StockControl, StockControlDto> {

    @Override
    public StockControl createEntity(StockControlDto stockControlDto) {
        return getModelMapper().map(stockControlDto, StockControl.class);
    }

    @Override
    public StockControlDto createDto(StockControl stockControl) {
        return getModelMapper().map(stockControl, StockControlDto.class);
    }
}
