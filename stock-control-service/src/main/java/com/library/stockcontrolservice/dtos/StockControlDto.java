package com.library.stockcontrolservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StockControlDto {
    private Long id;
    private int minimum;
    private int current;
    private Long productId;
}
