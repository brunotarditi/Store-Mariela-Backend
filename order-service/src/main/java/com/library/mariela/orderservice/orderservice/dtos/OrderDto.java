package com.library.mariela.orderservice.orderservice.dtos;

import com.library.commonsservice.dtos.BaseDto;
import com.library.mariela.orderservice.orderservice.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDto extends BaseDto {
    private double total;
    private Status status;
    private String paymentMethod;
    private List<OrderDetailDto> orderDetailDtos = new ArrayList<>();
}
