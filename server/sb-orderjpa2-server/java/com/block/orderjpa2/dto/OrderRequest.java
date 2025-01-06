package com.block.orderjpa2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    public String productName;
    public Integer quantity;
    public Double totalPrice;
}
