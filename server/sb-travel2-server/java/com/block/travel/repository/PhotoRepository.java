package com.block.travel.repository;

import com.block.travel.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long > {

    List<Photo> findByPlaceId(long placeId);
}
