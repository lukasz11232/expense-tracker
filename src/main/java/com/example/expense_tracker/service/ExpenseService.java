package com.example.expense_tracker.service;

import com.example.expense_tracker.dto.CreateExpensRequestDto;
import com.example.expense_tracker.dto.DeleteExpenseRequestDto;
import com.example.expense_tracker.dto.UpdateExpenseRequestDto;
import com.example.expense_tracker.entity.Expense;
import com.example.expense_tracker.entity.FilterPeriod;

import java.util.List;
import java.util.UUID;

public interface ExpenseService{
    void createExpense(UUID userId, CreateExpensRequestDto request);
    List<Expense> getExpense(UUID userId,FilterPeriod period);
    void updateExpense(UUID userId, UpdateExpenseRequestDto request);
    void deleteExpense(UUID userId, DeleteExpenseRequestDto request);
}
