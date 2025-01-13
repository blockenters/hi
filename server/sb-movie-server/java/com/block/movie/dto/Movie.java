package com.block.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    public String rank;
    public String rankInten;
    public String movieCd;
    public String movieName;
    public String openDate;
    public String salesAcc;
    public String audiAcc;
}
