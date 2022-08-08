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
public class AbstractDto {
    private Long id;
    private Date createAt;
    private Date updateAt;
    private boolean isDelete;
}
