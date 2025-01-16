package com.block.foodjpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageableResponse {

    public Integer page;
    public Integer size;
    public Long totalElements;
    public Integer totalPages;

}
