package com.block.jpareview.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductListResponse {
    public List<ProductResponse> content;
    public Integer page;
    public Integer size;
    public Integer totalPages;
    public Long totalElements;
}
