package com.example.expense_tracker.exception;

public class ExpenseNotFoundException extends RuntimeException {
    public ExpenseNotFoundException() { super(); }
    public ExpenseNotFoundException(String message) { super(message); }
    public ExpenseNotFoundException(String message, Throwable cause) { super(message, cause); }
}
