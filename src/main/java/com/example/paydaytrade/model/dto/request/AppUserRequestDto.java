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
public class AppUserRequestDto {

    @NotBlank(message = NAME_IS_URGENT )
    private String name;

    @NotBlank(message = EMAIL_IS_URGENT)
    @Email(message = EMAIL_IS_NOT_VALID)
    private String email;

    @NotBlank(message = PASSWORD_IS_NOT_VALID)
    @Size(min = 6, message = PASSWORD_REGEX)
    private String password;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
