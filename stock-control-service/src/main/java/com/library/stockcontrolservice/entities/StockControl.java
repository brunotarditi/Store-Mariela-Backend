package com.library.stockcontrolservice.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "stock_control")
public class StockControl {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "minimum")
    private int minimum;

    @Column(name = "current")
    private int current;

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;

    @Column(name = "update_at")
    @Temporal(TemporalType.DATE)
    private Date updateAt;

    @Column(name = "id_product", unique = true)
    private Long productId;

    @PrePersist
    public void createAt(){
        this.createAt = new Date();
        this.updateAt = new Date();
    }
}
