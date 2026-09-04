package com.example.expense_tracker.exception;

import com.example.expense_tracker.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.service.invoker.HttpRequestValues;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodNotAllowedException.class)
    public ResponseEntity<ErrorDto> handleMethodNotAllowed(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldError().getDefaultMessage();

        ErrorDto error = new ErrorDto(
                HttpStatus.BAD_REQUEST,
                "Method Not Allowed: " + message,
                Instant.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
    @ExceptionHandler(ExpenseNotFoundException.class)
    public ResponseEntity<ErrorDto> handleExpenseNotFound( ExpenseNotFoundException ex ) {
        ErrorDto error = new ErrorDto(
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                Instant.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error); // HTTP 404
    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorDto> handleBadCredentials(BadCredentialsException ex) {
        ErrorDto error = new ErrorDto(
                HttpStatus.UNAUTHORIZED,
                "Nieprawidłowy login lub hasło",
                Instant.now()
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error); // HTTP 401
    }
}
