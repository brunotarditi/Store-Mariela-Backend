package com.library.brandservice.entities;

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
@Table(name = "brands")
public class Brand  extends AbstractEntity implements Serializable {

    @Column(name = "name")
    private String name;

}
