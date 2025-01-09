package com.block.travel.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "travel_courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;
    @Column(length = 100)
    public String title;
    @Column(length = 50)
    public String region;
    @Column
    public Integer duration;
    @Column
    public Integer totalCost;
    @Column
    public Instant createdAt;

    @OneToMany(mappedBy = "course")  // Place Entity 의 course 멤버변수
    public List<Place> placeList = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.createdAt = Instant.now();
    }

}



