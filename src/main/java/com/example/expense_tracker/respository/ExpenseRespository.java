package com.example.expense_tracker.respository;

import com.example.expense_tracker.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExpenseRespository extends JpaRepository<Expense, UUID>{

}
