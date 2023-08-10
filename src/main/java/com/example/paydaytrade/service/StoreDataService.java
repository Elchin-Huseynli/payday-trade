package com.example.paydaytrade.service;

import com.example.paydaytrade.model.dto.request.ProductRequestDto;
import com.example.paydaytrade.model.dto.request.StockDataRequestDTO;
import java.util.List;

public interface StoreDataService {

    StockDataRequestDTO fetchAndStoreData();

    List<ProductRequestDto> register();
    Object findAll();

}
