package com.library.mariela.orderservice.orderservice.dtos;

import com.library.commonsservice.dtos.AbstractDto;
import com.library.mariela.orderservice.orderservice.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDto extends AbstractDto {
    private double total;
    private Status status;
}
