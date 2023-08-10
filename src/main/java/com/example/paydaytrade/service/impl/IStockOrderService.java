package com.example.paydaytrade.service.impl;

import com.example.paydaytrade.helper.EmailServiceHelper;
import com.example.paydaytrade.model.dto.request.EmailRequestDto;
import com.example.paydaytrade.model.dto.request.StockOrderRequest;
import com.example.paydaytrade.model.entity.StockOrder;
import com.example.paydaytrade.repository.StockOrderRepository;
import com.example.paydaytrade.service.AppUserService;
import com.example.paydaytrade.service.EmailService;
import com.example.paydaytrade.service.StockOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IStockOrderService implements StockOrderService {
    private final StockOrderRepository stockOrderRepository;
    private final AppUserService userService;
    private final EmailService emailService;
    private final EmailServiceHelper emailServiceHelper;

    @Override
    public void executeOrdersWhenPriceMet(StockOrderRequest orderRequest) {

        List<StockOrder> ordersToExecute = stockOrderRepository.findStockOrdersByStatusAndBrandAndLessThanOrEqualTargetPrice(true, orderRequest.getStockSymbol(), orderRequest.getTargetPrice());

        for (StockOrder order : ordersToExecute) {
            try {
                userService.executeOrderAndUpdateBalance(order, orderRequest.getTargetPrice());
                order.setStatus(false);
                stockOrderRepository.save(order);
                EmailRequestDto emailRequestDto = emailServiceHelper.operationSendEmail(order);
                emailService.sendEmail(emailRequestDto);
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
    }
}
