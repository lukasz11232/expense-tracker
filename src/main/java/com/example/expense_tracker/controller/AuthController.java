package com.example.expense_tracker.controller;

import com.example.expense_tracker.dto.LoginRequestDto;
import com.example.expense_tracker.dto.RegisterRequestDto;
import com.example.expense_tracker.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthService> register(@Valid @RequestBody RegisterRequestDto dto) {
        AuthService response = authService.register(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthService> login(@Valid @RequestBody LoginRequestDto dto) {
        AuthService response = authService.login(dto);
        return ResponseEntity.ok(response);
    }
}