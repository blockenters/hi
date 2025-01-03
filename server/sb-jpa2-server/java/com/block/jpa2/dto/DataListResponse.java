package com.block.jpa2.dto;

import com.block.jpa2.entity.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
public class DataListResponse {
    public List<Data> itemList;
}
