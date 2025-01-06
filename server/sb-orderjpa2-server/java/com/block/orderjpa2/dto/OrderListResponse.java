package com.block.orderjpa2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderListResponse {
    public List<OrderItemResponse> orderList;
    public PageInfo pageInfo;
}
