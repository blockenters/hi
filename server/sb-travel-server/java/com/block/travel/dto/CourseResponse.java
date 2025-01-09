package com.block.travel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponse {
    private Long id;
    private String title;
    private UserResponse writer;
    private String region;
    private Integer duration;
    private Integer totalCost;
    public List<PlaceResponse> places = new ArrayList<>();
    private String createdAt;
}
