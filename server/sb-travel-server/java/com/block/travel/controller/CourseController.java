package com.block.travel.controller;

import com.block.travel.dto.CourseResponse;
import com.block.travel.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {
    @Autowired
    CourseService courseService;

    @GetMapping("/api/v1/courses/{courseId}")
    public ResponseEntity<CourseResponse> getCourse(@PathVariable long courseId ){
        CourseResponse courseResponse = courseService.getCourse(courseId);
        return ResponseEntity.status(200).body(courseResponse);
    }

}
