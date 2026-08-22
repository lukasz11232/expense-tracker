package com.example.expense_tracker.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Setter
@Getter

@Entity
@Table(name = "users")
public class User{

    @Id
    @Column(name = "user_id",nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "email",nullable = false, unique = true)
    private String email;

    @Column(name = "passwordHash",nullable = false)
    private String passwordHash;

    @Column( name = "createdAt",nullable = false)
    private Instant createdAt;

    public User(){
    }

    public User(UUID id,String email,String passwordHash){
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
        this.createdAt =Instant.now();
    }

    @Override
    public boolean equals(Object o){
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        return Objects.equals(id,user.id);
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(id);
    }
}
