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
public abstract class AbstractDto {
    protected Long id;
    protected Date createAt;
    protected Date updateAt;
    protected boolean isDelete;
}
