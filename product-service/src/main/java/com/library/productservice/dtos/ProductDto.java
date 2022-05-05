package com.library.productservice.dtos;

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
    @NotNull
    private Double price;
    private Long brandId;
    private Long categoryId;
    private Date createAt;
    private Date updateAt;
}
