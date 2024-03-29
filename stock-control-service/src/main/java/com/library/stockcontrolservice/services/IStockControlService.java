package com.library.stockcontrolservice.services;

import com.library.commonsservice.services.ICommonService;
import com.library.stockcontrolservice.dtos.StockControlDto;
import com.library.stockcontrolservice.entities.StockControl;

import java.util.Optional;


public interface IStockControlService extends ICommonService<StockControl, StockControlDto> {
    Optional<StockControlDto> getStockControlByProductId(Long productId);
}
