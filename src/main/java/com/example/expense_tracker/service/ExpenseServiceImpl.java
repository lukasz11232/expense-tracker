package com.example.expense_tracker.service;

import com.example.expense_tracker.dto.CreateExpensRequestDto;
import com.example.expense_tracker.entity.Expense;
import com.example.expense_tracker.entity.FilterPeriod;
import com.example.expense_tracker.entity.User;
import com.example.expense_tracker.mapper.ExpenseMapper;
import com.example.expense_tracker.respository.ExpenseRespository;
import com.example.expense_tracker.respository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ExpenseServiceImpl implements ExpenseService{

    private final ExpenseRespository expenseRepository;
    private final UserRepository userRepository;
    private final ExpenseMapper expenseMapper;

    public ExpenseServiceImpl(
            ExpenseRespository expenseRepository,
            UserRepository userRepository,
            ExpenseMapper expenseMapper
    ){
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
        this.expenseMapper = expenseMapper;
    }


    @Override
    @Transactional
    public void createExpense(UUID userId,CreateExpensRequestDto request){
        var user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found: " + userId));

        Expense expense = expenseMapper.toEntity(request, user);
        expenseRepository.save(expense);
    }

    @Override
    public void getExpense(UUID userId,FilterPeriod period){

    }

    @Override
    public void updateExpense(UUID userId,CreateExpensRequestDto request){

    }

    @Override
    public void deleteExpense(UUID userId,CreateExpensRequestDto request){

    }
}
