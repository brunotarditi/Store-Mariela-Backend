package com.library.stockcontrolservice.entities;

import com.library.commonsservice.entities.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "stock_control")
public class StockControl extends AbstractEntity implements Serializable {

    @Column(name = "minimum")
    private int minimum;

    @Column(name = "current")
    private int current;

    @Column(name = "sale_percentage")
    private int percent;

    @Column(name = "list_price")
    private double listOfPrice;

    @Column(name = "id_product", unique = true)
    private Long productId;


}
