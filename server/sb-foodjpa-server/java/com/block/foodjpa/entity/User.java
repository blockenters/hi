package com.block.foodjpa.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(length = 100, unique = true)
    public String email;
    @Column(length = 256)
    public String password;
    @Column(length = 100)
    public String nickname;
    @Column(length = 100)
    public String role;
    @Column
    public Instant createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = Instant.now();
    }
}






