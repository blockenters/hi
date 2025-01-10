package com.block.travel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseListResponse {
    public List<CoursePlaceResponse> content;
    public Integer page;
    public Integer size;
    public Long totalElements;
    public Integer totalPages;
}
