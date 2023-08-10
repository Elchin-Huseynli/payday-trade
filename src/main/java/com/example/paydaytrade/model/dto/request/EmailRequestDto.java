package com.example.paydaytrade.model.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import static com.example.paydaytrade.model.constants.Constants.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmailRequestDto {

    @NotBlank(message = EMAIL_IS_URGENT)
    @Email(message = EMAIL_IS_NOT_VALID)
    String to;

    @NotBlank(message = SUBJECT_IS_URGENT)
    @Size(max = 100, message = SUBJECT_REGEX)
    String subject;

    @NotBlank(message = BODY_IS_URGENT)
    String text;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
