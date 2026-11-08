package com.rigo.dev.technical_accenture.api.error_handler;


import java.util.ArrayList;
import java.util.List;

import com.rigo.dev.technical_accenture.api.errors.BaseErrorResponse;
import com.rigo.dev.technical_accenture.api.errors.ErrorsResp;
import com.rigo.dev.technical_accenture.api.exeptions.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

@RestControllerAdvice
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestController {

    @ExceptionHandler(WebExchangeBindException.class)
    public BaseErrorResponse handleBadRequest(WebExchangeBindException exception){

        List<String> errors = new ArrayList<>();

        exception.getAllErrors().forEach(error ->  errors.add(error.getDefaultMessage()));

        return ErrorsResp.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.name())
                .errors(errors)
                .build();
    }

    @ExceptionHandler(BadRequestException.class)
    public BaseErrorResponse badRequest(BadRequestException exception){

        List<String> errors = new ArrayList<>();

        errors.add(exception.getMessage());

        return ErrorsResp.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.name())
                .errors(errors)
                .build();

    }

}