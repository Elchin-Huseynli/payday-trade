package com.example.paydaytrade.handler;

import com.example.paydaytrade.exception.GeneralException;
import com.example.paydaytrade.model.dto.response.ExceptionResponse;
import com.example.paydaytrade.model.enums.Exceptions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.Date;


@RestControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ExceptionResponse> handle(GeneralException applicationException) {
        Exceptions exceptions = applicationException.getExceptions();

        return ResponseEntity
                .status(exceptions.getStatus())
                .body(ExceptionResponse.builder()
                        .message(exceptions.getMessage())
                        .timestamp(new Date(String.valueOf(LocalDateTime.now())))
                        .build());
    }
}
