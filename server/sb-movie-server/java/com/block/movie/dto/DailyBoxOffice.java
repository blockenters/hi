package com.block.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailyBoxOffice {
    public String rnum;
    public String rank;
    public String rankInten;
    public String rankOldAndNew;
    public String movieCd;
    public String movieNm;
    public String openDt;
    public String salesAmt;
    public String salesShare;
    public String salesInten;
    public String salesChange;
    public String salesAcc;
    public String audiCnt;
    public String audiInten;
    public String audiChange;
    public String audiAcc;
    public String scrnCnt;
    public String showCnt;
}