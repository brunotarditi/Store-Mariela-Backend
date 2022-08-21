package com.library.mariela.productservice.dtos;

import com.library.commonsservice.dtos.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StockControlDto extends BaseDto {
    private int minimum;
    private int current;
    private int percent;
    private double listOfPrice;
    private Long productId;
}
