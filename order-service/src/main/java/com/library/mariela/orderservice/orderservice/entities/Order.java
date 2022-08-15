package com.library.mariela.orderservice.orderservice.entities;

import com.library.commonsservice.entities.AbstractEntity;
import com.library.mariela.orderservice.orderservice.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "order")
public class Order extends AbstractEntity {

    @Transient
    private double total;

    @Column(name = "status")
    private Status status;
}
