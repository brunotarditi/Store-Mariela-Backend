package com.library.mariela.productservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HistoricalPurchase {
    private Long id;
    private int quantity;
    private double costPrice;
    private Date createAt;
    private Date updateAt;
    private Long productId;
}
