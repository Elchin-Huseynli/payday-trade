package com.example.paydaytrade.service;

import com.example.paydaytrade.model.dto.request.StockOrderRequest;

public interface StockOrderService {

    void executeOrdersWhenPriceMet(StockOrderRequest orderRequest);


}
