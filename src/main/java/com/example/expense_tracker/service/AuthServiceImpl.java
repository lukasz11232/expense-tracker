package com.example.expense_tracker.service;

import com.example.expense_tracker.config.JwtService;
import com.example.expense_tracker.dto.LoginRequestDto;
import com.example.expense_tracker.dto.RegisterRequestDto;
import com.example.expense_tracker.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthServiceImpl(UserRepository userRepository,PasswordEncoder passwordEncoder,JwtService jwtService){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    @Transactional
    public AuthService register(RegisterRequestDto registerRequestDto){


        return null;
    }

    @Override
    public AuthService login(LoginRequestDto loginRequestDto){
        return null;
    }
}
