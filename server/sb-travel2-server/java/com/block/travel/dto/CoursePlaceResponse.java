package com.block.travel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoursePlaceResponse {

    public Long  id;
    public String title;
    public String region;
    public Integer duration;
    public Integer totalCost;
    public UserResponse writer;
    public Integer placeCount;
    public String createdAt;

}
