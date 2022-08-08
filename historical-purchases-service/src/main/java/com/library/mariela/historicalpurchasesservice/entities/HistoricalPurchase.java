package com.library.mariela.historicalpurchasesservice.entities;

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
@Table(name = "historical_purchase")
public class HistoricalPurchase extends AbstractEntity implements Serializable {

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "cost_price")
    private double costPrice;

    @Column(name = "has_iva")
    private boolean hasIva;

    @Column(name = "id_product")
    private Long productId;

}
