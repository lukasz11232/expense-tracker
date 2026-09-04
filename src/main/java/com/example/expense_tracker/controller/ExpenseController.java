package com.example.expense_tracker.controller;

import com.example.expense_tracker.entity.Expense;
import com.example.expense_tracker.service.ExpenseService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Setter
@Getter

@RestController
@RequestMapping("/api/v1/expenses")
public class ExpenseController{
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }
    @PostMapping
    public ResponseEntity<ExpenseController> createExpense(@AuthenticationPrincipal UUID userId,@Valid @RequestBody CreateExpenseRequestDto dto){
        return ResponseEntity.ok(this);
    }
}
