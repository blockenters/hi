package com.block.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatResponse {

    public TotalResponse total;
    public List<DateResponse> byDate;
    public List<CategoryResponse> byCategory;
}
