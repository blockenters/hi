package com.order.orderjpa.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Data
@Entity
@Table(name = "`order`")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(length = 200)
    public String productName;

    @Column
    public Integer quantity;

    @Column
    public Double totalPrice;

    @Column
    public Instant orderDate;

    @PrePersist
    public void prePersist(){
        orderDate = Instant.now();
    }
}








