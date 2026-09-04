package com.example.expense_tracker.service;

import com.example.expense_tracker.dto.CreateExpensRequestDto;

import java.util.UUID;

public interface ExpenseService{
    void createExpense(UUID userId, CreateExpensRequestDto request);
    void getExpense(UUID userId, CreateExpensRequestDto request);
    void updateExpense(UUID userId, CreateExpensRequestDto request);
    void deleteExpense(UUID userId, CreateExpensRequestDto request);
}
