package com.example.expense_tracker.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequestDto(
        @NotBlank(message = "Email is required")
        @Email(message = "Email is not valid")//Nie potrzebe pray logownaiu, ale zwiększa walidacje
            String email,
        @NotBlank(message = "Password is required")
            String password
){
}
