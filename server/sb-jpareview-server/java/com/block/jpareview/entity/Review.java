package com.block.jpareview.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Data
@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    public Product product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;
    @Column
    public Integer rating;
    @Column(length = 2000)
    public String content;
    @Column
    public Instant createdAt;
    @Column
    public Instant updatedAt;

}








