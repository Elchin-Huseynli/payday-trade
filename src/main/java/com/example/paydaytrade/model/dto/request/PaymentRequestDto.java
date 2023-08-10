package com.example.paydaytrade.model.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import static com.example.paydaytrade.model.constants.Constants.*;

@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentRequestDto {

    @NotBlank(message = EMAIL_IS_URGENT)
    @Email(message = EMAIL_IS_NOT_VALID)
    String email;

    @Positive(message = PAYMENT_REGEX)
    @DecimalMin(value = "1.00", message = PAYMENT_AMOUNT_REGEX)
    @Min(value = 1, message = PAYMENT_AMOUNT_REGEX)
    BigDecimal payment;

}