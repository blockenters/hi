package com.block.jpareview.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(length = 100)
    public String name;
    @Column(length = 2000)
    public String description;
    @Column
    public Integer price;
    @Column(length = 50)
    public String category;
    @Column
    public Integer stockQuantity;
    @Column
    public Instant createdAt;
}




