package com.example.paydaytrade.helper;

import com.example.paydaytrade.model.entity.AppUser;
import com.example.paydaytrade.model.entity.Token;
import com.example.paydaytrade.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppUserServiceHelper {

    private final TokenRepository tokenRepository;

    public void saveUserToken(AppUser appUser, String jwtToken) {
        Token token = Token.builder()
                .appUser(appUser)
                .token(jwtToken)
                .revoked(false)
                .expired(false)
                .build();

        tokenRepository.save(token);
    }

    public void revokedAllUserTokens(AppUser appUser) {
        List<Token> validUserTokens = tokenRepository.findAllValidTokensByUser(appUser.getId());

        if (validUserTokens.isEmpty()) return;

        validUserTokens.forEach(t -> {t.setExpired(true); t.setRevoked(true);});
        tokenRepository.saveAll(validUserTokens);
    }

}
