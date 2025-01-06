package com.block.orderjpa2.entity;

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
    @Column(length = 100)
    public String productName;
    @Column
    public Integer quantity;
    @Column
    public Double totalPrice;
    @Column
    public Instant orderDate;

    @PrePersist
    public void prePersist(){
        // UTC 현재 시간.
        orderDate = Instant.now();
    }

}
