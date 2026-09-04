package com.example.expense_tracker.dto;

import org.springframework.http.HttpStatus;
import org.springframework.web.service.invoker.HttpRequestValues;

import java.time.Instant;

public record ErrorDto(
        HttpStatus status,
        String error,
        Instant timestamp
){
}
