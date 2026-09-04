package com.example.expense_tracker.exception;

import java.util.UUID;

public class ExpenseNotFoundExpection extends RuntimeException{
    public ExpenseNotFoundExpection(UUID id){
        super("Expense not found with ID: " + id);
    }
}
