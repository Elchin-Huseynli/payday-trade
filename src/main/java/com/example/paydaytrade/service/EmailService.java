package com.example.paydaytrade.service;

import com.example.paydaytrade.model.dto.request.EmailRequestDto;
import com.example.paydaytrade.model.dto.response.EmailResponseDto;
import com.example.paydaytrade.model.dto.response.GeneralResponse;

public interface EmailService {

    void sendEmail(EmailRequestDto emailDTO);

    GeneralResponse<EmailResponseDto> confirmEmail(String token);
}
