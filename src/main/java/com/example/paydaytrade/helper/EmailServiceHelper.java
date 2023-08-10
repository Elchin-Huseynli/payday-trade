package com.example.paydaytrade.helper;

import com.example.paydaytrade.model.dto.request.EmailRequestDto;
import com.example.paydaytrade.model.entity.AppUser;
import com.example.paydaytrade.model.entity.ConfirmationToken;
import com.example.paydaytrade.model.entity.StockOrder;
import com.example.paydaytrade.repository.ConfirmationTokenRepository;
import com.example.paydaytrade.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailServiceHelper {

    private final EmailService emailService;
    private final ConfirmationTokenRepository confirmationTokenRepository;

    public void confirmationToken(AppUser appUser) {
        ConfirmationToken confirmationToken = confirmationTokenBuild(appUser);
        confirmationTokenRepository.save(confirmationToken);
        confirmationTokenRepository.save(confirmationToken);
        emailService.sendEmail(confirmationTokenSendEmail(appUser,confirmationToken));
    }


    public EmailRequestDto confirmationTokenSendEmail(AppUser appUser , ConfirmationToken confirmationToken){
        String url = "http://localhost:8080/payday-trade/user/confirmation-account?token=" + confirmationToken.getToken();
        return EmailRequestDto
                .builder()
                .to(appUser.getEmail())
                .subject("Registration")
                .text("<p> Hi, " + appUser.getName() + ", <p>" +
                        "<p>Thank you for registering with us," + "" +
                        "Please, follow the link below to complete your registration.<p>" +
                        "<a href=\"" + url + "\">Verify your email to active your account<a>" +
                        "<p> Thank you <br> Users Registration Portal Service")
                .build();
    }

    public ConfirmationToken confirmationTokenBuild (AppUser appUser){
        String token = UUID.randomUUID().toString();

        return ConfirmationToken
                .builder()
                .confirm(true)
                .token(token)
                .user(appUser)
                .confirmedAt(LocalDateTime.now()) // true olduqdan sonra indiki vaxt olmalidir emailde
                .createdAt(LocalDateTime.now())
                .build();


    }

    public EmailRequestDto operationSendEmail(StockOrder order){
        return EmailRequestDto
                .builder()
                .to(order.getUser().getEmail())
                .subject("Operation ")
                .text("<p> Hi, " + order.getUser().getEmail() + ", <p>" +
                        "<p>Thank you for organization with us," + "" +
                        "\n" +
                        "Your order of  " + order.getUser().getEmail() +  " stock has been placed. Order type:" +  order.getOrderType() +",Order quantity: " +order.getQuantity() +  " Order symbol: " +order.getStockSymbol() + "  Target price: " + order.getTargetPrice() + "<p>" +
                        "<p> Thank you <br> Users Operation Portal Service")
                .build();
    }
}
