package com.block1.news.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsSearchResponse {
    public Integer totalCount;
    public List<NewsResponse> articles;
}
