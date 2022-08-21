package com.library.mariela.orderdetailservice.orderdetailservice.dtos;

import com.library.commonsservice.dtos.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDetailDto extends BaseDto {
    private int quantity;
    private double subtotal;
    private Long idOrder;
    private Long idProduct;
}
