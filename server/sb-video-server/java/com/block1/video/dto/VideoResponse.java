package com.block1.video.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoResponse {
    public String videoId;
    public String title;
    public String videoUrl;
    public String thumbnailUrl;
    public String channelTitle;
    public String publishedAt;
    public Long viewCount;
}
