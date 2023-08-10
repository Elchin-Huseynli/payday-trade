package com.example.paydaytrade.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Status {

    USER_REGISTER_SUCCESSFULLY("User register Successfully"),

    SHOWING_SUCCESSFULLY("Showing is successfully"),

    USER_ACTIVATION_SUCCESSFULLY("User activation successfully! "),

    INCREMENT_IS_SUCCESSFULLY("Increment is successfully"),

    USER_UPDATE_SUCCESSFULLY("User update successfully"),

    USER_LOGIN_SUCCESSFULLY("User login successfully!"),
    ORDERS_EXECUTION_TRIGGERED("Orders execution triggered!");


    private final String message;

}
