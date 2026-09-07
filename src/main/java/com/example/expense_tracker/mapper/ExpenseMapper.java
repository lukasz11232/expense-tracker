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
        // This mapper overload should not assign the user's id as the expense id.
        // Keep id null here; callers that perform updates should load the existing entity and modify it.
        return new Expense(
                null,
                user,
                dto.title(),
                dto.amount(),
                dto.category(),
                dto.date()
        );
    }
}
