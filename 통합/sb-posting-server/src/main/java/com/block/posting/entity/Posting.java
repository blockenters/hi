package com.block.posting.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Posting {
    public Long id;
    public Long userId;
    public String imgUrl;
    public String content;
    public String createdAt;
    public String email;
    public Integer isLike;
}
