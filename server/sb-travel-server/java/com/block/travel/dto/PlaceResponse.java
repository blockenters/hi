package com.block.travel.dto;

import com.block.travel.entity.Photo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceResponse {
    public Long id;
    public String name;
    public String address;
    public Integer visitOrder;
    public String description;
    public Integer cost;
    public List<PhotoResponse> photos = new ArrayList<>();
}






