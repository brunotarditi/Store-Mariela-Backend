package com.library.commonsservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BaseDto {
    protected Long id;
    protected Date createAt;
    protected Date updateAt;
    protected boolean isEnabled;
}
