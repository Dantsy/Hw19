package ru.otus.reactive.service.exceptions;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorDto {

    private final String code;

    private final LocalDateTime date;

    public ErrorDto(String code) {
        this.code = code;
        this.date = LocalDateTime.now();
    }

    public ErrorDto(String code, LocalDateTime date) {
        this.code = code;
        this.date = date;
    }
}