package com.library.mariela.orderdetailservice.orderdetailservice.entities;

import com.library.commonsservice.entities.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "order_detail")
public class OrderDetail extends AbstractEntity {

    @Column(name = "quantity")
    private int quantity;
    @Column(name = "subtotal")
    private double subtotal;
    @Column(name = "id_order")
    private Long idOrder;
    @Column(name = "id_product")
    private Long idProduct;

}
