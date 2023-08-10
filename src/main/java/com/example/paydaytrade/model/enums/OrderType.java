package com.example.paydaytrade.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderType {
    BUY("buy"),
    SELL("sell");

    String name;
}
