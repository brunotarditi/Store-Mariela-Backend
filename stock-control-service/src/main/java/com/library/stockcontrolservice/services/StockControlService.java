package com.library.stockcontrolservice.services;

import com.library.commonsservice.services.CommonService;
import com.library.stockcontrolservice.dtos.StockControlDto;
import com.library.stockcontrolservice.entities.StockControl;
import com.library.stockcontrolservice.factories.StockFactory;
import com.library.stockcontrolservice.repositories.IStockControlRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class StockControlService extends CommonService<StockControl, IStockControlRepository, StockControlDto> implements IStockControlService {

    public StockControlService(IStockControlRepository repository, StockFactory stockFactory) {
        super(repository, stockFactory);
    }

    @Override
    public Optional<StockControlDto> getStockControlByProductId(Long productId) {
        Optional<StockControl> stockControl = this.repository.findStockControlByProductId(productId);
        return stockControl
                .stream()
                .map(iFactory::createDto)
                .findFirst();
    }
}
