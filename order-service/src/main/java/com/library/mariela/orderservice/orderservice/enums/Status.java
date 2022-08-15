package com.library.mariela.orderservice.orderservice.enums;

public enum Status {
    NEW_ORDER(1),
    DENY(2),
    OK(3),
    PREPARATION(4),
    CANCELLED(5),
    DELIVERED(6);

    private final Integer status;

    Status(Integer status){
        this.status = status;
    }

    public Integer getStatus(){
        return status;
    }
}
