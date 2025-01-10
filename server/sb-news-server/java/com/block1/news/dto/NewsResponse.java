package com.block1.news.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsResponse {
    public String  title;
    public String originallink;
    public String link;
    public String description;
    public String pubDate;

}
