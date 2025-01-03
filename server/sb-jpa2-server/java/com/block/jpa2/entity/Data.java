package com.block.jpa2.entity;

import jakarta.persistence.*;

import java.time.Instant;

@lombok.Data
@Entity
@Table(name = "data")
public class Data {
    @Id
    @GeneratedValue(generator = "increment")
    public Long id;
    @Column(length = 30)
    public String name;
    @Column(length = 200, nullable = false)
    public String content;
    @Column
    public Instant createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = Instant.now();
    }

}





