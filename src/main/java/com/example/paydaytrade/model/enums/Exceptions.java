package com.example.paydaytrade.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum Exceptions {

    USER_IS_ALREADY_EXISTS(HttpStatus.ALREADY_REPORTED, "User is already exists!"),

    TOKEN_IS_UNREACHABLE(HttpStatus.NOT_FOUND,"Token is unreachable!"),

    TOKEN_IS_INVALID_EXCEPTION(HttpStatus.BAD_REQUEST, "Token is invalid!"),
    NOT_ENOUGH_STOCK(HttpStatus.NOT_FOUND,"Not enough stock!"),
    USERNAME_NOT_FOUND(HttpStatus.NOT_FOUND,"Username not found!");


    private final HttpStatus status;
    private final String message;
}
