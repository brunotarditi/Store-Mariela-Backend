package com.library.mariela.productservice.customExceptions;

public class MyException extends RuntimeException{
    private String message;

    public MyException(String message) {
        super(message);
    }
}
