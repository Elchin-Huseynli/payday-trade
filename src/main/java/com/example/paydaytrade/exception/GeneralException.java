package com.example.paydaytrade.exception;


import com.example.paydaytrade.model.enums.Exceptions;
import lombok.Getter;

@Getter
public class GeneralException extends RuntimeException {

    private final Exceptions exceptions;

    public GeneralException(Exceptions exceptions) {
        super(exceptions.getMessage());
        this.exceptions = exceptions;
    }

}
