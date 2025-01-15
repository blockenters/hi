package com.block.foodjpa.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Data
@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    public Restaurant restaurant;
    @ManyToOne
    @JoinColumn(name = "menu_id")
    public Menu menu;
    @Column
    public Integer rating;
    @Column(length = 100)
    public String content;
    @Column(length = 200)
    public String imageUrl;
    @Column
    public Instant createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = Instant.now();
    }
}








