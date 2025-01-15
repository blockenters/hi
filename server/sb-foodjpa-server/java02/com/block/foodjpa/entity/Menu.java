package com.block.foodjpa.entity;

import jakarta.persistence.*;
import lombok.Data;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

import java.time.Instant;
import java.util.List;

@Data
@Entity
@Table(name = "menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    public Restaurant restaurant;
    @Column(length = 100)
    public String name;
    @Column
    public Integer price;
    @Column(length = 100)
    public String description;
    @Column(length = 100)
    public String category;
    @Column
    public Instant createdAt;

    @OneToMany(mappedBy = "menu")
    public List<Review> reviewList;

    @PrePersist
    public void prePersist() {
        createdAt = Instant.now();
    }
}








