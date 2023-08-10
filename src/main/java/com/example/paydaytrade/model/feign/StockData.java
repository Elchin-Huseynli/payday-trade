package com.example.paydaytrade.model.feign;

import com.example.paydaytrade.model.dto.request.StockDataRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "StockDataService", url = "https://dummyjson.com")
public interface StockData {

        @GetMapping("/products")
        StockDataRequestDTO fetchData();
}
