package com.library.brandservice.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "brands")
public class Brand implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "createAt")
    @Temporal(TemporalType.DATE)
    private Date createAt;

    @Column(name = "updateAt")
    @Temporal(TemporalType.DATE)
    private Date updateAt;

    @PrePersist
    public void createAt(){
        this.createAt = new Date();
        this.updateAt = new Date();
    }
}
