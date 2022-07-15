package com.library.mariela.productservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDto {

    private Long id;
    @NotBlank
    private String name;
    private Double price;
    @NotNull
    private Long brandId;
    @NotNull
    private Long categoryId;
    private Date createAt;
    private Date updateAt;
    private StockControlDto stockControl;
}
