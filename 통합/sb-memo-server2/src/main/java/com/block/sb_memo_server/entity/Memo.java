package com.block.sb_memo_server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Memo {
    public Long id;
    public Long userId;
    public String title;
    public String content;
    public String memoDate;
    public String createdAt;
}
