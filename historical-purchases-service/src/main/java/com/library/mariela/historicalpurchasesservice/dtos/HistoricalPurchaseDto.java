package com.library.mariela.historicalpurchasesservice.dtos;

import com.library.commonsservice.dtos.AbstractDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HistoricalPurchaseDto extends AbstractDto {

    private int quantity;
    private double costPrice;
    private boolean hasIva;
    private Long productId;
}
