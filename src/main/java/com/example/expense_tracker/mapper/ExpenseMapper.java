package com.example.expense_tracker.mapper;

import com.example.expense_tracker.dto.CreateExpenseRequestDto;
import com.example.expense_tracker.dto.ExpenseDto;
import com.example.expense_tracker.dto.UpdateExpenseRequestDto;
import com.example.expense_tracker.entity.Expense;
import com.example.expense_tracker.entity.User;
import org.springframework.stereotype.Component;

@Component
public class ExpenseMapper {
    public ExpenseDto toDto(Expense entity) {
        return new ExpenseDto(
                entity.getId(),
                entity.getTitle(),
                entity.getAmount(),
                entity.getCategory(),
                entity.getCreatedAt()
        );
    }

    public Expense toEntity(CreateExpenseRequestDto dto, User user) {
        return new Expense(
                null,
                user,
                dto.title(),
                dto.amount(),
                dto.category(),
                dto.date()
        );
    }

    public Expense toEntity(UpdateExpenseRequestDto dto, User user) {
        return new Expense(
                user.getId(),
                user,
                dto.title(),
                dto.amount(),
                dto.category(),
                dto.date()
        );
    }
}
