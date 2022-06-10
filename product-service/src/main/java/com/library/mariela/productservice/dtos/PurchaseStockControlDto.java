package com.library.mariela.productservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PurchaseStockControlDto {
    private Long id;
    private int minimum;
    private int current;
    private int percent;
    private boolean hasIva;
    private int quantity;
    private double costPrice;
    private Date createAt;
    private Date updateAt;
    private Long productId;
}
