package com.example.expense_tracker.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthResponseDto(
        @NotBlank(message = "Token is required")
            String token
){
}
