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
public class StockControl {
    private Long id;
    private int minimum;
    private int current;
    private Date createAt;
    private Date updateAt;
    private Long productId;
}
