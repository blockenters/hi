package com.block.travel.service;

import com.block.travel.dto.CourseResponse;
import com.block.travel.dto.PhotoResponse;
import com.block.travel.dto.PlaceResponse;
import com.block.travel.dto.UserResponse;
import com.block.travel.entity.Course;
import com.block.travel.entity.Photo;
import com.block.travel.entity.Place;
import com.block.travel.repository.CourseRepository;
import com.block.travel.repository.PhotoRepository;
import com.block.travel.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
