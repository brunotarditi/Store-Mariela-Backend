package com.library.mariela.productservice.entities;

import com.library.commonsservice.entities.BaseEntity;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
public class Product extends BaseEntity implements Serializable {

    @Column(name = "name")
    private String name;

    @Transient
    private Double price;

    @Column(name = "id_brand")
    private Long brandId;

    @Column(name = "id_category")
    private Long categoryId;



}
