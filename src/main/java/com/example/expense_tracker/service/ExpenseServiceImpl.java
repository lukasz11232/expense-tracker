package com.example.expense_tracker.service;

import com.example.expense_tracker.dto.CreateExpensRequestDto;
import com.example.expense_tracker.dto.UpdateExpenseRequestDto;
import com.example.expense_tracker.entity.Expense;
import com.example.expense_tracker.entity.FilterPeriod;
import com.example.expense_tracker.mapper.ExpenseMapper;
import com.example.expense_tracker.respository.ExpenseRespository;
import com.example.expense_tracker.respository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
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
    private LocalDate getDateFromPeriod(FilterPeriod period){
        LocalDate today = LocalDate.now();
        LocalDate startDate;


        switch(period){
            case PAST_WEEK :
                startDate = today.minusWeeks(1);
                break;
            case PAST_MONTH :
                startDate = today.minusMonths(1);
                break;
            case PAST_YEAR  :
                startDate = today.minusYears(1);
                break;
            case CUSTOM:
                //TODO: Implement custom period logic
                throw new UnsupportedOperationException("Custom period is not implemented yet.");
            default :
                throw new IllegalArgumentException("Invalid period: " + period);
        }
        return startDate;
    }

    @Override
    public List<Expense> getExpense(UUID userId,FilterPeriod period){

        LocalDate today = LocalDate.now();
        LocalDate startDate;

        startDate = getDateFromPeriod(period);


        return  expenseRepository.FindAllByUserIdDateBetween(userId, startDate,today);
    //TODO: Check type of period and return the expenses accordingly

    }

    @Override
    public void updateExpense(UUID userId, UpdateExpenseRequestDto request){
        var user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found: " + userId));

    }

    @Override
    public void deleteExpense(UUID userId,DeleteExpenseRequestDto request){

    }
}
