package com.example.expense_tracker.service;

import com.example.expense_tracker.dto.CreateExpenseRequestDto;
import com.example.expense_tracker.dto.DeleteExpenseRequestDto;
import com.example.expense_tracker.dto.UpdateExpenseRequestDto;
import com.example.expense_tracker.entity.Expense;
import com.example.expense_tracker.entity.FilterPeriod;
import com.example.expense_tracker.mapper.ExpenseMapper;
import com.example.expense_tracker.repository.ExpenseRepository;
import com.example.expense_tracker.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;
    private final ExpenseMapper expenseMapper;

    public ExpenseServiceImpl(
            ExpenseRepository expenseRepository,
            UserRepository userRepository,
            ExpenseMapper expenseMapper
    ) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
        this.expenseMapper = expenseMapper;
    }

    @Override
    @Transactional
    public void createExpense(UUID userId, CreateExpenseRequestDto request) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + userId));

        Expense expense = expenseMapper.toEntity(request, user);
        expenseRepository.save(expense);
    }

    private LocalDate getDateFromPeriod(FilterPeriod period) {
        LocalDate today = LocalDate.now();
        LocalDate startDate;

        switch (period) {
            case PAST_WEEK:
                startDate = today.minusWeeks(1);
                break;
            case PAST_MONTH:
                startDate = today.minusMonths(1);
                break;
            case PAST_YEAR:
                startDate = today.minusYears(1);
                break;
            case CUSTOM:
                throw new UnsupportedOperationException("Custom period is not implemented yet.");
            default:
                throw new IllegalArgumentException("Invalid period: " + period);
        }
        return startDate;
    }

    @Override
    public List<Expense> getExpense(UUID userId, FilterPeriod period) {
        LocalDate today = LocalDate.now();
        LocalDate startDate = getDateFromPeriod(period);

        return expenseRepository.findByUser_IdAndExpenseDateBetween(userId, startDate, today);
    }

    @Override
    public void updateExpense(UUID userId, UpdateExpenseRequestDto request) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + userId));
        expenseRepository.save(expenseMapper.toEntity(request, user));
    }

    @Override
    public void deleteExpense(UUID userId, DeleteExpenseRequestDto request) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + userId));
        expenseRepository.deleteByIdAndUserId(request.id(), userId);
    }

    @Override
    public List<Expense> getAllExpenses(){
        return expenseRepository.findAll();
    }
}
