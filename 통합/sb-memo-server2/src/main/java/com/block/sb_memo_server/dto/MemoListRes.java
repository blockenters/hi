package com.block.sb_memo_server.dto;

import com.block.sb_memo_server.entity.Memo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemoListRes {

    public List<Memo> items;
    public Integer count;
}
