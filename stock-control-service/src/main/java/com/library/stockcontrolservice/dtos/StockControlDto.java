package com.library.stockcontrolservice.dtos;

import com.library.commonsservice.entities.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StockControlDto extends BaseEntity {
    @NotNull
    private Integer minimum;
    @NotNull
    private Integer current;
    @NotNull
    private Integer percent;
    private double listOfPrice;
    private Long productId;
}
