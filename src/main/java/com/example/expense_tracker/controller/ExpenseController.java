package com.example.expense_tracker.controller;

import com.example.expense_tracker.dto.CreateExpenseRequestDto;
import com.example.expense_tracker.dto.DeleteExpenseRequestDto;
import com.example.expense_tracker.dto.UpdateExpenseRequestDto;
import com.example.expense_tracker.entity.Expense;
import com.example.expense_tracker.entity.FilterPeriod;
import com.example.expense_tracker.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public ResponseEntity<Void> createExpense(
            @AuthenticationPrincipal UUID userId,
            @Valid @RequestBody CreateExpenseRequestDto dto
    ) {
        expenseService.createExpense(userId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Expense>> getExpenses(
            @AuthenticationPrincipal UUID userId,
            @RequestParam FilterPeriod period
    ) {
        List<Expense> expenses = expenseService.getExpense(userId, period);
        return ResponseEntity.ok(expenses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateExpense(@AuthenticationPrincipal UUID userId,@Valid @RequestBody UpdateExpenseRequestDto dto) {
        expenseService.updateExpense(userId, dto);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(
            @AuthenticationPrincipal UUID userId,
            DeleteExpenseRequestDto request
    ) {
        expenseService.deleteExpense(userId, request);
        return ResponseEntity.noContent().build();
    }

}
