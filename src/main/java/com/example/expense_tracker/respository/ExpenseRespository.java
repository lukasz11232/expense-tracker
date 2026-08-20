package com.example.expense_tracker.respository;

import com.example.expense_tracker.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ExpenseRespository extends JpaRepository<Expense, UUID>{
    List FindAllByUserIdDateBetween(UUID userId,LocalDate startDate,LocalDate endDate);
    Optional findByIdAndUserId(UUID id,UUID userId);
    void deleteByIdAndUserId(UUID id,UUID userId);
}
