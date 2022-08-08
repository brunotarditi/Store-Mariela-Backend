package com.library.commonsservice.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;

    @Column(name = "update_at")
    @Temporal(TemporalType.DATE)
    private Date updateAt;

    @Column(name = "is_delete")
    private boolean isDelete;

    @PrePersist
    public void createAt(){
        this.createAt = new Date();
        this.updateAt = new Date();
    }
}
