package com.block.travel.entity;

import jakarta.persistence.*;
import lombok.Data;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "places")
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @ManyToOne
    @JoinColumn(name = "course_id")
    public Course course;
    @Column(length = 100)
    public String name;
    @Column(length = 255)
    public String address;
    @Column
    public Integer visitOrder;
    @Column(length = 300)
    public String description;
    @Column
    public Integer cost;
    @Column
    public Instant createdAt;

    @OneToMany(mappedBy = "place")  // Photo 클래스의 멤버변수 place를 가르킨다.
    public List<Photo> photoList = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.createdAt = Instant.now();
    }
}
