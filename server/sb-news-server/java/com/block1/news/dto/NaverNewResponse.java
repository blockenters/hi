package com.block1.news.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NaverNewResponse {

    public String lastBuildDate;
    public Integer total;
    public Integer start;
    public Integer display;
    public List<NewsResponse> items;

}
