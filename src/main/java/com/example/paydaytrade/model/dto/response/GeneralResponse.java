package com.example.paydaytrade.model.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GeneralResponse<T> {

    String message;

    HttpStatus status;

    Date timeStamp;

    T data;

    public static <T> GeneralResponse<T> of(T data, HttpStatus status) {
        return buildGeneralResponse(data, status, status.name());
    }

    public static <T> GeneralResponse<T> of(T data, String message) {
        return buildGeneralResponse(data, HttpStatus.OK, message);
    }

    private static <T> GeneralResponse<T> buildGeneralResponse(T data, HttpStatus status, String message) {
        return GeneralResponse.<T>builder()
                .data(data)
                .status(status)
                .message(message)
                .timeStamp(new Date())
                .build();
    }

}
