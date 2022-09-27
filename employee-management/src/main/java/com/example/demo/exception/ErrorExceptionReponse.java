package com.example.demo.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ErrorExceptionReponse {
    private HttpStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime localDateTime;

    private String message;

    public ErrorExceptionReponse(HttpStatus badRequest, String error) {
        this();
        this.status = badRequest;
        this.message = error;
    }

    public ErrorExceptionReponse() {
        localDateTime = LocalDateTime.now();
    }



    @Override
    public String toString() {
        return "ErrorExceptionReponse{" +
                "status=" + message +
                ", localDateTime=" + localDateTime +
                ", message='" + status + '\'' +
                '}';
    }
}
