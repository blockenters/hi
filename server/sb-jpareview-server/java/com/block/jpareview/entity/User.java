package com.block.jpareview.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(length = 50, unique = true)
    public String email;
    @Column(length = 256)
    public String password;
    @Column(length = 30)
    public String nickname;
    @Column
    public Instant createdAt;

    @PrePersist
    public void prePersist(){
        createdAt = Instant.now();
    }

}




