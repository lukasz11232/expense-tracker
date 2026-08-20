package com.example.expense_tracker.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Setter
@Getter

@Entity
@Table(name = "expenses")
public class Expense{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "amount",nullable = false)
    private BigDecimal amount;

    @Column(name = "category",nullable = false)
    @Enumerated(EnumType.STRING)
    private ExpenseCategory category;

    @Column(name = "expenseDate", nullable = false)
    private LocalDate expenseDate;

    @Column(name = "createdAt",nullable = false, updatable = false)
    private Instant createdAt;

    public Expense(){
    }

    public Expense(UUID id,User user,String title,BigDecimal amount,ExpenseCategory category,LocalDate expenseDate){
        this.id = id;
        this.user = user;
        this.title = title;
        this.amount = amount;
        this.category = category;
        this.expenseDate = expenseDate;
        this.createdAt = Instant.now();
    }

    @Override
    public boolean equals(Object o){
        if (o == null || getClass() != o.getClass()) return false;

        Expense expense = (Expense) o;
        return Objects.equals(id,expense.id);
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(id);
    }
}
