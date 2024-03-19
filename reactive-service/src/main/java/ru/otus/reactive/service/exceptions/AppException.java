package ru.otus.reactive.service.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AppException extends RuntimeException {

    private final String code;

    private final HttpStatus status;

    public AppException(String code) {
        super(code);
        this.code = code;
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public AppException(String code, HttpStatus status) {
        super(code);
        this.code = code;
        this.status = status;
    }
}