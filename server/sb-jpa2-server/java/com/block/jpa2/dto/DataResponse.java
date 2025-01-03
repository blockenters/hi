package com.block.jpa2.dto;

import com.block.jpa2.entity.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
public class DataResponse {

    public Data item;
}
