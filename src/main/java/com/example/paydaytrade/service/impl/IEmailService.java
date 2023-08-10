package com.example.paydaytrade.service.impl;

import com.example.paydaytrade.exception.GeneralException;
import com.example.paydaytrade.model.dto.request.EmailRequestDto;
import com.example.paydaytrade.model.dto.response.EmailResponseDto;
import com.example.paydaytrade.model.dto.response.GeneralResponse;
import com.example.paydaytrade.model.entity.AppUser;
import com.example.paydaytrade.model.entity.ConfirmationToken;
import com.example.paydaytrade.model.enums.Exceptions;
import com.example.paydaytrade.model.enums.Status;
import com.example.paydaytrade.repository.AppUserRepository;
import com.example.paydaytrade.repository.ConfirmationTokenRepository;
import com.example.paydaytrade.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.mail.internet.MimeMessage;


@Slf4j
@Service
@RequiredArgsConstructor
public class IEmailService implements EmailService {

    private final JavaMailSender mailSender;
    private final AppUserRepository appUserRepository;
    private final ConfirmationTokenRepository confirmationRepo;

    @SneakyThrows
    public void sendEmail(EmailRequestDto emailDTO) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(emailDTO.getTo());
        mimeMessageHelper.setSubject(emailDTO.getSubject());
        mimeMessageHelper.setText(emailDTO.getText(), true);

        mailSender.send(mimeMessage);
    }

    public GeneralResponse<EmailResponseDto> confirmEmail(String confirmationToken) {
        ConfirmationToken token = confirmationRepo.findByToken(confirmationToken);

        log.info("confirmation token : " + confirmationToken);

        if (token != null) {
            AppUser appUser = appUserRepository.findByEmailIgnoreCase(token.getUser().getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("Username nor found " + token.getUser().getEmail()));

            log.info("Find email : " + token.getUser().getEmail());
            appUser.setEnable(true);
            appUserRepository.save(appUser);
            log.info("Email verified successfully!");

            return GeneralResponse.of(EmailResponseDto.builder()
                    .to(appUser.getEmail()).build(), Status.USER_ACTIVATION_SUCCESSFULLY.getMessage());
        }

        throw new GeneralException(Exceptions.TOKEN_IS_UNREACHABLE);
    }

}
