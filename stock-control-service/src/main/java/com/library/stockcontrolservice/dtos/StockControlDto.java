package com.library.stockcontrolservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotNull;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StockControlDto {
    private Long id;
    @NotNull
    private Integer minimum;
    @NotNull
    private Integer current;
    @NotNull
    private Integer percent;
    private double listOfPrice;
    private Date createAt;
    private Date updateAt;
    private Long productId;
}
