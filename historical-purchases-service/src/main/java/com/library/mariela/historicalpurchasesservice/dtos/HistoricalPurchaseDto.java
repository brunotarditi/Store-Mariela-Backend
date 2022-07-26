package com.library.mariela.historicalpurchasesservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HistoricalPurchaseDto {

    private Long id;
    private int quantity;
    private double costPrice;
    private boolean hasIva;
    private Date createAt;
    private Date updateAt;
    private Long productId;
}
