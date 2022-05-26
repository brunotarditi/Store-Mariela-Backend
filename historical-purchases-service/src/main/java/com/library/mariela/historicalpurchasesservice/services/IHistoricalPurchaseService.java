package com.library.mariela.historicalpurchasesservice.services;

import com.library.commonsservice.services.ICommonService;
import com.library.mariela.historicalpurchasesservice.dtos.HistoricalPurchaseDto;
import com.library.mariela.historicalpurchasesservice.entities.HistoricalPurchase;

import java.util.List;

public interface IHistoricalPurchaseService extends ICommonService<HistoricalPurchase, HistoricalPurchaseDto> {

    List<HistoricalPurchaseDto> getHistoricalPurchaseByProductId(Long id);
}
