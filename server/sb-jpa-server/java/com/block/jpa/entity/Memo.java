package com.block.jpa.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "memo")
public class Memo {
    @Id
    @GeneratedValue(generator = "increment")
    public Long id;
    @Column(length = 200, nullable = false)
    public String content;
    @Column(nullable = false)
    public Instant createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = Instant.now();
    }

}





