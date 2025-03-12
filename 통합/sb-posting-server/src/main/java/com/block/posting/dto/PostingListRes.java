package com.block.posting.dto;

import com.block.posting.entity.Posting;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostingListRes {
    public List<Posting> items;
    public int count;
}
