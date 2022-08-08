package com.library.mariela.productservice.entities;

import com.library.commonsservice.entities.AbstractEntity;
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
public class Product extends AbstractEntity implements Serializable {

    @Column(name = "name")
    private String name;

    @Transient
    private Double price;

    @Column(name = "id_brand")
    private Long brandId;

    @Column(name = "id_category")
    private Long categoryId;



}
