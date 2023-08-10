package com.example.paydaytrade.model.dto.response;

import com.example.paydaytrade.model.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentBillResponse {

    String message;

    Date paymentTime;

    String username;

    BigDecimal includedMoney;

    BigDecimal totalDepositBalance;

    public static <T> PaymentBillResponse of(String username , BigDecimal includedMoney, BigDecimal totalDepositBalance) {
        return buildGeneralResponse(username, includedMoney,totalDepositBalance);
    }
    private static <T> PaymentBillResponse buildGeneralResponse(String username , BigDecimal includedMoney, BigDecimal totalDepositBalance) {
        return PaymentBillResponse.builder()
                .message(Status.INCREMENT_IS_SUCCESSFULLY.getMessage())
                .username(username)
                .paymentTime(new Date())
                .includedMoney(includedMoney)
                .totalDepositBalance(totalDepositBalance)
                .build();
    }

}
