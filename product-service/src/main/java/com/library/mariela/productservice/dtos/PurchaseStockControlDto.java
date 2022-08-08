package com.library.mariela.productservice.dtos;

import com.library.commonsservice.dtos.AbstractDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PurchaseStockControlDto extends AbstractDto {
    private int minimum;
    private int current;
    private int percent;
    private boolean hasIva;
    private int quantity;
    private double costPrice;
    private double listOfPrice;
    private Long productId;
}
