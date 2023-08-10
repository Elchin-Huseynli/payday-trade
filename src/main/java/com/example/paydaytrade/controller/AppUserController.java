package com.example.paydaytrade.controller;

import com.example.paydaytrade.model.dto.request.AppUserRequestDto;
import com.example.paydaytrade.model.dto.response.GeneralResponse;
import com.example.paydaytrade.service.AppUserService;
import com.example.paydaytrade.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/payday-trade/user")
@RequiredArgsConstructor
@Slf4j
public class AppUserController {

    private final AppUserService appUserService;
    private final EmailService emailService;

    @PostMapping("/registration")
    public GeneralResponse<?> registerUser(@RequestBody AppUserRequestDto user) {
        log.info("Registering user: {}", user.getName());
        return appUserService.signUp(user);
    }

    @PostMapping("/login")
    public GeneralResponse<?> login(@RequestBody AppUserRequestDto user) {
        log.info("Login user: {}", user.getName());
        return appUserService.login(user);
    }


    @GetMapping("/refresh-token")
    public GeneralResponse<?> refreshToken(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String token) {
        return appUserService.refreshToken(token);
    }

    @RequestMapping(value="/confirmation-account", method= {RequestMethod.GET, RequestMethod.POST})
    public GeneralResponse<?> confirmUserAccount(@RequestParam("token") String confirmationToken) {
        log.info("Confirming user account with token: {}", confirmationToken);
        return emailService.confirmEmail(confirmationToken);
    }

}
