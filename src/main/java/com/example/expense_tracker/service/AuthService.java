package com.example.expense_tracker.service;

import com.example.expense_tracker.dto.LoginRequestDto;
import com.example.expense_tracker.dto.RegisterRequestDto;

public interface AuthService{
    AuthService register(RegisterRequestDto registerRequestDto);
    AuthService login(LoginRequestDto loginRequestDto);

}
