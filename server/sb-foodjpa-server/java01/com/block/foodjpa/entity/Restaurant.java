package com.block.foodjpa.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Entity
@Table(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(length = 100)
    public String name;
    @Column(length = 200)
    public String address;
    @Column(length = 100)
    public String phone;
    @Column(length = 100)
    public String category;
    @Column(length = 100)
    public String description;
    @Column
    public Instant createdAt;

    @OneToMany(mappedBy = "restaurant")
    public List<Menu> menuList;

    @OneToMany(mappedBy = "restaurant")
    public List<Review> reviewList;


    @PrePersist
    public void prePersist() {
        this.createdAt = Instant.now();
    }
}












