package com.block1.video.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoListResponse {
    public Integer totalCount;
    public List<VideoResponse> videos;
}
