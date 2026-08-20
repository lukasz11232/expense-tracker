package com.example.expense_tracker.dto;

import com.example.expense_tracker.entity.ExpenseCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateExpensRequestDto(
        @NotBlank(message = "Title is required")
            String title,
        @NotNull(message = "Amount is required")
                @Positive( message = "Amount must be positive")
            BigDecimal amount,
        @NotBlank(message = "Category is required")
            ExpenseCategory category,
        @NotBlank(message = "Date is required")
            LocalDate date
){
}
