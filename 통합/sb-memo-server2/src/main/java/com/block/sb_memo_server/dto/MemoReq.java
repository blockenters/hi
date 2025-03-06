package com.block.sb_memo_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemoReq {
    public String title;
    public String content;
    public String memoDate;
}
