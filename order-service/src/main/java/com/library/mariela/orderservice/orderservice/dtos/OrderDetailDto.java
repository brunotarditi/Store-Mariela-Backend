package com.library.mariela.orderservice.orderservice.dtos;

import com.library.commonsservice.dtos.AbstractDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDetailDto extends AbstractDto {
    private int quantity;
    private double subtotal;
    private int idOrder;
}
