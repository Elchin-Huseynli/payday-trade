package com.example.paydaytrade.controller;
import com.example.paydaytrade.model.dto.request.ProductRequestDto;
import com.example.paydaytrade.model.dto.response.GeneralResponse;
import com.example.paydaytrade.model.dto.response.ProductResponseDto;
import com.example.paydaytrade.model.enums.Status;
import com.example.paydaytrade.service.impl.IStoreDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequestMapping("/payday-trade/stock-data")
@RestController
@RequiredArgsConstructor
public class StockDataController {
    private final IStoreDataService storeDataService;

    @PostMapping("/registration")
    public GeneralResponse<List<ProductRequestDto>> register(){
        List<ProductRequestDto> products = storeDataService.register();
        log.info("Products : {}",products);
        return GeneralResponse.of(products, HttpStatus.CREATED);
    }

    @GetMapping("/fetching")
    public GeneralResponse<Page<ProductResponseDto>> findAllProduct() {
        log.info(" All product ");
        Page<ProductResponseDto> products = storeDataService.findAll();
        log.info("Products {}",products);
        return  GeneralResponse.of(products, Status.SHOWING_SUCCESSFULLY.getMessage());

    }

}

