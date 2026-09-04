package com.example.expense_tracker.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record DeleteExpenseRequestDto(
        @NotNull(message = "Expense ID is required")
    UUID id
){

}
