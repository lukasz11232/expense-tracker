package com.example.expense_tracker.respository;

import com.example.expense_tracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID>{
    Optional findByEmail(String email);
    boolean existsByEmail(String email);
}
