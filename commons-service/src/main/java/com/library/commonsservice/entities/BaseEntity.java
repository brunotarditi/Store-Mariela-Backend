package com.library.commonsservice.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class BaseEntity {

    protected static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    protected Date createAt;

    @Column(name = "update_at")
    @Temporal(TemporalType.DATE)
    protected Date updateAt;

    @Column(name = "is_enabled")
    protected boolean isEnabled;

    @PrePersist
    public void createAt(){
        this.createAt = new Date();
        this.updateAt = new Date();
    }
}
