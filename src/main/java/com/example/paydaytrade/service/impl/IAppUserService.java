package com.example.paydaytrade.service.impl;

import com.example.paydaytrade.exception.GeneralException;
import com.example.paydaytrade.helper.AppUserServiceHelper;
import com.example.paydaytrade.helper.EmailServiceHelper;
import com.example.paydaytrade.helper.SecurityHelper;
import com.example.paydaytrade.mapper.UserMapper;
import com.example.paydaytrade.model.dto.request.AppUserRequestDto;
import com.example.paydaytrade.model.dto.response.AuthenticationResponse;
import com.example.paydaytrade.model.dto.response.GeneralResponse;
import com.example.paydaytrade.model.entity.AppUser;
import com.example.paydaytrade.model.entity.StockOrder;
import com.example.paydaytrade.model.enums.Exceptions;
import com.example.paydaytrade.model.enums.OrderType;
import com.example.paydaytrade.model.enums.Status;
import com.example.paydaytrade.repository.AppUserRepository;
import com.example.paydaytrade.service.AppUserService;
import com.example.paydaytrade.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;


@Slf4j
@Service
@RequiredArgsConstructor
public class IAppUserService implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final EmailServiceHelper emailService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final AppUserServiceHelper appUserServiceHelper;
    private final SecurityHelper securityHelper;

    @SneakyThrows
    @Override
    public GeneralResponse<AuthenticationResponse> signUp(AppUserRequestDto appUserRequestDto) {
        AppUser appUser = userMapper.userDtoToUser(appUserRequestDto);

        if (appUserRepository.existsByEmail(appUser.getEmail())) {
            throw new GeneralException(Exceptions.USER_IS_ALREADY_EXISTS);
        }

        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUserRepository.save(appUser);
        emailService.confirmationToken(appUser);

        String accessToken = jwtService.generateToken(appUser);
        String refreshToken = jwtService.generateRefreshToken(appUser);

        appUserServiceHelper.saveUserToken(appUser, accessToken);

        AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

        return GeneralResponse.of(authenticationResponse, Status.USER_REGISTER_SUCCESSFULLY.getMessage());
    }

    @Override
    public GeneralResponse<AuthenticationResponse> login(AppUserRequestDto appUserRequestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(appUserRequestDto.getEmail(), appUserRequestDto.getPassword()));

        AppUser appUser = appUserRepository.findByEmailIgnoreCase(appUserRequestDto.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Username not found " + appUserRequestDto.getEmail()));

        String accessToken = jwtService.generateToken(appUser);
        String refreshToken = jwtService.generateRefreshToken(appUser);

        appUserServiceHelper.revokedAllUserTokens(appUser);
        appUserServiceHelper.saveUserToken(appUser, accessToken);

        AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

        return GeneralResponse.of(authenticationResponse, Status.USER_LOGIN_SUCCESSFULLY.getMessage());

    }

    @Override
    public GeneralResponse<AuthenticationResponse> refreshToken(String authHeader) {
        if (!securityHelper.authHeaderIsValid(authHeader)) {
            throw new GeneralException(Exceptions.TOKEN_IS_INVALID_EXCEPTION);
        }

        String jwt = authHeader.substring(7);
        String username = jwtService.extractUsername(jwt);

        if (username != null) {
            AppUser appUser = appUserRepository.findByEmailIgnoreCase(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Username doesn't exist: " + username));

            if (jwtService.isTokenValid(jwt, appUser)) {
                String accessToken = jwtService.generateToken(appUser);
                String refreshToken = jwtService.generateRefreshToken(appUser);

                appUserServiceHelper.revokedAllUserTokens(appUser);
                appUserServiceHelper.saveUserToken(appUser, accessToken);

                AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();

                return GeneralResponse.of(authenticationResponse, HttpStatus.OK);

            }
        }

        throw new GeneralException(Exceptions.TOKEN_IS_INVALID_EXCEPTION);
    }

    @Override
    @Transactional
    public void executeOrderAndUpdateBalance(StockOrder order, BigDecimal currentPrice) {
        AppUser user = appUserRepository.findAppUserByEmailAndIsEnable(order.getUser().getEmail(), true)
                .orElseThrow(() -> new GeneralException(Exceptions.USERNAME_NOT_FOUND));

        BigDecimal transactionAmount = order.getTargetPrice().multiply(BigDecimal.valueOf(order.getQuantity()));

        if (OrderType.BUY.equals(order.getOrderType())) {
            user.setDepositBalance(user.getDepositBalance().subtract(transactionAmount));

            user.getStocks().put(order.getStockSymbol(), user.getStocks().getOrDefault(order.getStockSymbol(), 0) + order.getQuantity());

        } else if (OrderType.SELL.equals(order.getOrderType())) {
            user.setDepositBalance(user.getDepositBalance().add(transactionAmount));

            int ownedStockQuantity = user.getStocks().getOrDefault(order.getStockSymbol(), 0);
            if (ownedStockQuantity >= order.getQuantity()) {
                user.getStocks().put(order.getStockSymbol(), ownedStockQuantity - order.getQuantity());
            } else {
                throw new GeneralException(Exceptions.NOT_ENOUGH_STOCK);
            }
        }
        appUserRepository.save(user);
    }

}
