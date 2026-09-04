package com.example.expense_tracker.exception;

public class ExpenseNotFoundException extends RuntimeException {

    public ExpenseNotFoundException(Long id) {
        super("Nie znaleziono wydatku o ID: " + id);
    }

    public ExpenseNotFoundException(String message) {
        super(message);
    }
}