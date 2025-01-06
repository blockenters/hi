package com.block.orderjpa2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageInfo {
    public Integer page;
    public Integer size;
    public Long totalElements;
    public Integer totalPages;
}
