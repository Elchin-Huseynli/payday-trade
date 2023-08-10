package com.example.paydaytrade.service.impl;

import com.example.paydaytrade.exception.GeneralException;
import com.example.paydaytrade.mapper.UserMapper;
import com.example.paydaytrade.model.dto.request.PaymentRequestDto;
import com.example.paydaytrade.model.dto.response.AppUserResponseDto;
import com.example.paydaytrade.model.dto.response.GeneralResponse;
import com.example.paydaytrade.model.entity.AppUser;
import com.example.paydaytrade.model.enums.Exceptions;
import com.example.paydaytrade.model.enums.Status;
import com.example.paydaytrade.repository.AppUserRepository;
import com.example.paydaytrade.service.OperationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public  class IOperationService implements OperationService {

    private final AppUserRepository appUserRepository;
    private final UserMapper userMapper;

    @Override
    public GeneralResponse<AppUserResponseDto> makePayment(PaymentRequestDto paymentRequestDto) {
        Optional<AppUser> optionalAppUser = appUserRepository.findAppUserByEmailAndIsEnable(paymentRequestDto.getEmail(), true);

        if (optionalAppUser.isPresent()) {
            AppUser appUser = optionalAppUser.get();
            appUserRepository.updateDepositBalance(paymentRequestDto.getPayment(), appUser.getEmail());
            AppUserResponseDto appUserResponseDto = userMapper.userToUserResponseDto(appUser);

            return GeneralResponse.of(appUserResponseDto, Status.USER_UPDATE_SUCCESSFULLY.getMessage());
        }
        else {
            throw new GeneralException(Exceptions.USERNAME_NOT_FOUND);
        }
    }
}
