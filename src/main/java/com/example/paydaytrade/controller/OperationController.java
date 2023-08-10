package com.example.paydaytrade.controller;

import com.example.paydaytrade.model.dto.request.PaymentRequestDto;
import com.example.paydaytrade.model.dto.request.StockOrderRequest;
import com.example.paydaytrade.model.dto.response.AppUserResponseDto;
import com.example.paydaytrade.model.dto.response.GeneralResponse;
import com.example.paydaytrade.model.dto.response.PaymentBillResponse;
import com.example.paydaytrade.model.enums.Status;
import com.example.paydaytrade.service.OperationService;
import com.example.paydaytrade.service.StockOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/payday-trade/operation")
@RequiredArgsConstructor
public class OperationController {

    private final OperationService operationService;

    private final StockOrderService stockOrderService;

    @PutMapping
    public PaymentBillResponse payment(@RequestBody @Validated PaymentRequestDto requestDto){
        log.info("username {} -  payment {}" ,requestDto.getEmail() , requestDto.getPayment());
        GeneralResponse<AppUserResponseDto> responseDto =  operationService.makePayment(requestDto);
        return PaymentBillResponse.of(requestDto.getEmail(),requestDto.getPayment(),responseDto.getData().getDepositBalance().add(requestDto.getPayment()));
    }

    @GetMapping("/execute")
    public GeneralResponse<StockOrderRequest> executeOrdersWhenPriceMet(StockOrderRequest orderRequest) {
        stockOrderService.executeOrdersWhenPriceMet(orderRequest);
        return GeneralResponse.of(orderRequest, Status.ORDERS_EXECUTION_TRIGGERED.getMessage());
    }
}
