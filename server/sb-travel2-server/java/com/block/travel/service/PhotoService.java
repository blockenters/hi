package com.block.travel.service;

import com.block.travel.entity.Photo;
import com.block.travel.entity.Place;
import com.block.travel.repository.PhotoRepository;
import com.block.travel.repository.PlaceRepository;
import com.block.travel.util.UniqueFileNameGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.util.Optional;

@Service
public class PhotoService {
    @Value("${cloud.aws.s3.bucket}")
    String bucketName;
    @Autowired
    S3Client s3Client;
    @Autowired
    PhotoRepository photoRepository;
    @Autowired
    PlaceRepository placeRepository;

    public void uploadPhoto(long placeId, MultipartFile photo, String description){
       if(description != null){
           // 설명이 있는경우
           // 파일이름을 유니크하게 생성한다.
           String fileName =
                   UniqueFileNameGenerator.generateUniqueFileName(placeId, ".jpg");

           PutObjectRequest putObjectRequest =
                   PutObjectRequest.builder()
                           .bucket(bucketName)
                           .key(fileName)
                           .contentLength(photo.getSize())
                           .contentType(photo.getContentType())
                           .acl(ObjectCannedACL.PUBLIC_READ)
                           .build();
           try{
               s3Client.putObject(putObjectRequest,
                       RequestBody.fromInputStream(photo.getInputStream(),
                               photo.getSize()));
           } catch (Exception e) {
               throw new RuntimeException();
           }

           String photoUrl = "https://" + bucketName + ".s3.amazonaws.com/" + fileName;

           Photo photoEntity = new Photo();
           Optional<Place> place = placeRepository.findById(placeId);
           if(!place.isPresent()){
               throw new RuntimeException();
           }
           photoEntity.place = place.get();
           photoEntity.photoUrl = photoUrl;
           photoEntity.description = description;
           photoRepository.save(photoEntity);
       }else{
           // 설명이 없는 경우

       }
    }
}
