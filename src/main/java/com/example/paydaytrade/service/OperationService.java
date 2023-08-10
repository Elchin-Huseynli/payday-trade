package com.example.paydaytrade.service;

import com.example.paydaytrade.model.dto.request.PaymentRequestDto;
import com.example.paydaytrade.model.dto.response.AppUserResponseDto;
import com.example.paydaytrade.model.dto.response.GeneralResponse;

public interface OperationService {

    GeneralResponse<AppUserResponseDto> makePayment(PaymentRequestDto requestDto);


}
