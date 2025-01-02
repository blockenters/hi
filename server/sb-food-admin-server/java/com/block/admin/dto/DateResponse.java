package com.block.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DateResponse {
    public String date;
    public Integer reviewCount;
    public Double averageRating;
}
