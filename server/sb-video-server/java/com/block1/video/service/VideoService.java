package com.block1.video.service;

import com.block1.video.dto.VideoResponse;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class VideoService {

    @Autowired
    YouTube youTube;

    @Value("${youtube.api.key}")
    String apiKey;

    public com.block1.video.dto.VideoListResponse getVideos(String keyword){
        // 유투브 검색 api 명세서 확인.
        try {
            YouTube.Search.List search =
                    youTube.search().list("id,snippet");
            search.setKey(apiKey);
            search.setQ(keyword);
            search.setType("video");
            search.setMaxResults(20L);
            search.setOrder("date");

            // 실행해서 결과 받아오기
            SearchListResponse searchResponse = search.execute();
            List<SearchResult> searchResults = searchResponse.getItems();

            // 비디오 ID 목록 생성
            List<String> videoIds = new ArrayList<>();
            for( SearchResult result  :  searchResults){
                videoIds.add( result.getId().getVideoId() );
            }

            // 비디오의 정보를 요청.
            // statistics,snippet 으로 요청!
            // 비디오의 통계 데이터(조회수, 좋아요 수)와 기본정보(제목,설명,썸네일)
            YouTube.Videos.List videoRequest =
                            youTube.videos().list("statistics,snippet");
            videoRequest.setKey(apiKey);
            videoRequest.setId( String.join(",", videoIds) );

            // 요청 실행
            VideoListResponse videoResponse = videoRequest.execute();
            List<Video> videoList = videoResponse.getItems();

            // 우리 DTO로 변환해서 컨트롤러에 리턴.
            List<VideoResponse> videoResponseList = new ArrayList<>();

            for ( Video video : videoList){
                VideoResponse videoResponse1 = new VideoResponse();
                videoResponse1.videoId = video.getId();
                videoResponse1.title = video.getSnippet().getTitle();
                videoResponse1.videoUrl =
                        "https://www.youtube.com/watch?v=" + video.getId();
                videoResponse1.thumbnailUrl =
                        video.getSnippet().getThumbnails().getHigh().getUrl();
                videoResponse1.channelTitle =
                        video.getSnippet().getChannelTitle();
                videoResponse1.publishedAt =
                        video.getSnippet().getPublishedAt().toString();
                videoResponse1.viewCount =
                        video.getStatistics().getViewCount().longValue();

                videoResponseList.add(videoResponse1);
            }

            com.block1.video.dto.VideoListResponse videoListResponse =
                    new com.block1.video.dto.VideoListResponse();
            videoListResponse.totalCount = videoResponseList.size();
            videoListResponse.videos = videoResponseList;
            return videoListResponse;


        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
