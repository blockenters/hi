package com.block.travel.service;

import com.block.travel.dto.*;
import com.block.travel.entity.Course;
import com.block.travel.entity.Photo;
import com.block.travel.entity.Place;
import com.block.travel.repository.CourseRepository;
import com.block.travel.repository.PhotoRepository;
import com.block.travel.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private PhotoRepository photoRepository;

    public CourseListResponse getAllCourses(int page, int size, String sort, String keyword){
        if(keyword == null){
            // 키워드 없는경우

            // 페이징 처리를 위해서 Pageable 만든다.
            String[] sortArray = sort.split(",");
            // sortArray[0] => "totalCost"
            // sortArray[1] => "desc"
            Sort.Direction direction = null;
            if(sortArray[1].equals("desc")){
                direction = Sort.Direction.DESC;
            }else{
                direction = Sort.Direction.ASC;
            }
            PageRequest pageRequest = PageRequest.of(page-1, size, Sort.by(direction, sortArray[0]));

            // DB에 쿼리한다.
            Page<Course> coursePage = courseRepository.findAll(pageRequest);

            // 결과를 DTO로 만든다.
            ArrayList<CoursePlaceResponse> courseList = new ArrayList<>();
            for( Course course  : coursePage){
                CoursePlaceResponse coursePlaceResponse =
                        new CoursePlaceResponse();
                coursePlaceResponse.id = course.id;
                coursePlaceResponse.title = course.title;
                coursePlaceResponse.region = course.region;
                coursePlaceResponse.duration = course.duration;
                coursePlaceResponse.totalCost = course.totalCost;
                coursePlaceResponse.writer = new UserResponse(course.user.id, course.user.nickname);
                coursePlaceResponse.placeCount = course.placeList.size();
                coursePlaceResponse.createdAt = course.createdAt.toString();
                courseList.add(coursePlaceResponse);
            }

            CourseListResponse courseListResponse =
                                    new CourseListResponse();
            courseListResponse.content = courseList;
            courseListResponse.page = page;
            courseListResponse.size = size;
            courseListResponse.totalElements = coursePage.getTotalElements();
            courseListResponse.totalPages = coursePage.getTotalPages();

            return courseListResponse;

        }else{
            // 키워드 있는 경우
            // 페이징 처리를 위해서 Pageable 만든다.
            String[] sortArray = sort.split(",");
            // sortArray[0] => "totalCost"
            // sortArray[1] => "desc"
            Sort.Direction direction = null;
            if(sortArray[1].equals("desc")){
                direction = Sort.Direction.DESC;
            }else{
                direction = Sort.Direction.ASC;
            }
            PageRequest pageRequest = PageRequest.of(page-1, size, Sort.by(direction, sortArray[0]));

            Page<Course> coursePage = courseRepository.findByTitleContains( keyword, pageRequest);

            // 결과를 DTO로 만든다.
            ArrayList<CoursePlaceResponse> courseList = new ArrayList<>();
            for( Course course  : coursePage){
                CoursePlaceResponse coursePlaceResponse =
                        new CoursePlaceResponse();
                coursePlaceResponse.id = course.id;
                coursePlaceResponse.title = course.title;
                coursePlaceResponse.region = course.region;
                coursePlaceResponse.duration = course.duration;
                coursePlaceResponse.totalCost = course.totalCost;
                coursePlaceResponse.writer = new UserResponse(course.user.id, course.user.nickname);
                coursePlaceResponse.placeCount = course.placeList.size();
                coursePlaceResponse.createdAt = course.createdAt.toString();
                courseList.add(coursePlaceResponse);
            }

            CourseListResponse courseListResponse =
                    new CourseListResponse();
            courseListResponse.content = courseList;
            courseListResponse.page = page;
            courseListResponse.size = size;
            courseListResponse.totalElements = coursePage.getTotalElements();
            courseListResponse.totalPages = coursePage.getTotalPages();

            return courseListResponse;
        }
    }



    public CourseResponse getCourse(long courseId){
        // 1. db에서 데이터를 가져온다.
        Optional<Course> course = courseRepository.findById(courseId);
        if(!course.isPresent()){
            throw new RuntimeException();
        }
        // 2. DTO 로 만들자
        CourseResponse courseResponse =
                                    new CourseResponse();
        courseResponse.setId(course.get().id);
        courseResponse.setTitle(course.get().title);
        courseResponse.setWriter(  new UserResponse(course.get().user.id, course.get().user.nickname));
        courseResponse.setRegion( course.get().region );
        courseResponse.setDuration( course.get().duration );
        courseResponse.setTotalCost( course.get().totalCost );

        for( Place place : course.get().placeList ){
            PlaceResponse placeResponse = new PlaceResponse();
            placeResponse.id = place.id;
            placeResponse.name = place.name;
            placeResponse.address = place.address;
            placeResponse.visitOrder = place.visitOrder;
            placeResponse.cost = place.cost;
            placeResponse.description = place.description;
            for( Photo photo : place.photoList){
                PhotoResponse photoResponse = new PhotoResponse();
                photoResponse.id = photo.id;
                photoResponse.photoUrl = photo.photoUrl;
                photoResponse.description = photo.description;
                placeResponse.photos.add(photoResponse);
            }
            courseResponse.places.add(placeResponse);
        }
        courseResponse.setCreatedAt( course.get().createdAt.toString() );
        return courseResponse;
    }

}
