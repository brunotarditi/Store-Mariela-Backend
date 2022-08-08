package com.library.mariela.productservice.dtos;

import com.library.commonsservice.dtos.AbstractDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDto extends AbstractDto {

    @NotBlank
    private String name;
    private Double price;
    @NotNull
    private Long brandId;
    @NotNull
    private Long categoryId;
    private StockControlDto stockControl;
}
