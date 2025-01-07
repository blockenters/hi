package com.block.jpareview.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponse {
     public Long id;
     public String nickname;
     public Integer rating;
     public String content;
     public String createdAt;
     public String updatedAt;
}
