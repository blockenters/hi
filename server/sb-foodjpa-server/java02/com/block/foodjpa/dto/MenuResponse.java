package com.block.foodjpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuResponse {
    public Long id;
    public String name;
    public Integer price;
    public String description;
    public String category;
    public Integer reviewCount;
}
