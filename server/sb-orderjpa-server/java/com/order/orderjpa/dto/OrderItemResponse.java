package com.order.orderjpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemResponse {
    public Long id;
    public String productName;
    public Integer quantity;
    public Double totalPrice;
    public String orderDate;
}
