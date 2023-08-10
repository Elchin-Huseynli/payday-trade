package com.example.paydaytrade.helper;

import com.example.paydaytrade.repository.StockOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class StockMonitoringServiceHelper {

    private final StockOrderRepository stockOrderRepository;

    public BigDecimal getCurrentPrice() {
        return stockOrderRepository.getTargetAvgBalance();
    }
}
