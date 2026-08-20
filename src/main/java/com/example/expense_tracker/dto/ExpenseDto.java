package com.example.expense_tracker.dto;

import com.example.expense_tracker.entity.ExpenseCategory;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record ExpenseDto(
        UUID id,
        String title,
        BigDecimal amount,
        ExpenseCategory category,
        LocalDate date

){
}
