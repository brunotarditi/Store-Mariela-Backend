package com.library.productservice.entities;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "createAt")
    @Temporal(TemporalType.DATE)
    private Date createAt;

    @Column(name = "id_brand")
    private Long brandId;

    @PrePersist
    public void createAt(){
        this.createAt = new Date();
    }
}
