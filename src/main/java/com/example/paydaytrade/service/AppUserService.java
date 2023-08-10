package com.example.paydaytrade.service;

import com.example.paydaytrade.model.dto.request.AppUserRequestDto;
import com.example.paydaytrade.model.dto.response.AuthenticationResponse;
import com.example.paydaytrade.model.dto.response.GeneralResponse;
import com.example.paydaytrade.model.entity.StockOrder;

import java.math.BigDecimal;

public interface AppUserService {

    GeneralResponse<AuthenticationResponse> signUp(AppUserRequestDto appUserRequestDto);
    GeneralResponse<AuthenticationResponse> login(AppUserRequestDto appUserRequestDto);
    GeneralResponse<AuthenticationResponse> refreshToken(String authHeader);
    void executeOrderAndUpdateBalance(StockOrder order, BigDecimal currentPrice);

}
