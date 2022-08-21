package com.library.categoryservice.dtos;

import com.library.commonsservice.dtos.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDto extends BaseDto {
    private Long id;
    private String name;
    private Double price;
}
