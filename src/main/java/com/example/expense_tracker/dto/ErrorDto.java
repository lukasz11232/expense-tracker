package com.example.expense_tracker.dto;

import java.time.Instant;

public record ErrorDto(
        String error,
        Instant timestamp
){
}
