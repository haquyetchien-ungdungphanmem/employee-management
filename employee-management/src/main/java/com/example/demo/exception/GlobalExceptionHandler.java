package com.example.demo.exception;


import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;


@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = { Exception.class })
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorExceptionReponse forBidden(Exception ex){
        return new ErrorExceptionReponse(HttpStatus.FORBIDDEN,"403");
    }

    @ExceptionHandler(value = {IOException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorExceptionReponse badRequest(Exception ex){
        return new ErrorExceptionReponse(HttpStatus.BAD_REQUEST, "400");
    }

}
