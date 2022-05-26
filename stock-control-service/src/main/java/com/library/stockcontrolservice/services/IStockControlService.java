package com.library.stockcontrolservice.services;

import com.library.commonsservice.services.ICommonService;
import com.library.stockcontrolservice.dtos.StockControlDto;
import com.library.stockcontrolservice.entities.StockControl;

import java.util.List;

public interface IStockControlService extends ICommonService<StockControl, StockControlDto> {
    List<StockControlDto> getStockControlByProductId(Long productId);
}
