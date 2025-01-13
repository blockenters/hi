package com.block.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoxOfficeResult {
    public String boxofficeType;
    public String showRange;
    public List<DailyBoxOffice> dailyBoxOfficeList;
}
