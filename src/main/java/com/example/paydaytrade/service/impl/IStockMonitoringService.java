package com.example.paydaytrade.service.impl;

import com.example.paydaytrade.helper.StockMonitoringServiceHelper;
import com.example.paydaytrade.model.dto.request.StockOrderRequest;
import com.example.paydaytrade.service.StockOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IStockMonitoringService {

    private final StockOrderService stockOrderService;
    private final StockMonitoringServiceHelper helper;


    @Scheduled(fixedDelay = 10000)
    public void monitorStockPrices() {
        List<String> monitoredStockSymbols = Arrays.asList("Iphone", "Samsung");

        for (String stockSymbol : monitoredStockSymbols) {
            BigDecimal currentPrice = helper.getCurrentPrice();
            stockOrderService.executeOrdersWhenPriceMet(StockOrderRequest.builder()
                            .stockSymbol(stockSymbol)
                            .targetPrice(currentPrice)
                            .build());
        }
    }
}
