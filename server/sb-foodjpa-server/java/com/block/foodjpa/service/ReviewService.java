package com.block.foodjpa.service;

import com.block.foodjpa.config.JwtConfig;
import com.block.foodjpa.entity.Menu;
import com.block.foodjpa.entity.Restaurant;
import com.block.foodjpa.entity.Review;
import com.block.foodjpa.entity.User;
import com.block.foodjpa.repository.MenuRepository;
import com.block.foodjpa.repository.RestaurantRepository;
import com.block.foodjpa.repository.ReviewRepository;
import com.block.foodjpa.repository.UserRepository;
import com.block.foodjpa.util.UniqueFileNameGenerator;
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
public class ReviewService {
    @Autowired
    JwtConfig jwtConfig;
    @Value("${cloud.aws.s3.bucket}")
    String bucketName;
    @Autowired
    S3Client s3Client;
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    MenuRepository menuRepository;

    public void createReview(String token,
                 long restaurantId,
                 long menuId,
                 String strRating,
                 String content,
                 MultipartFile image){

        // 유저 아이디를 토큰에서 가져온다.
        long userId = Long.parseLong(jwtConfig.getTokenClaims(token.substring(7)).getSubject());

        String imageUrl = "";
        // image 가 있는 경우와 없는경우
        if (image != null){
            // S3 에 파일 저장.
            // 파일 이름을 유니크 하게 처리.
            String fileName = UniqueFileNameGenerator.generateUniqueFileName(userId, ".jpg");

            PutObjectRequest putObjectRequest =
                    PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(fileName)
                            .contentLength(image.getSize())
                            .contentType(image.getContentType())
                            .acl(ObjectCannedACL.PUBLIC_READ)
                            .build();
            try{
                s3Client.putObject(putObjectRequest,
                        RequestBody.fromInputStream(image.getInputStream(),
                                image.getSize()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            imageUrl = "https://"+bucketName+".s3.amazonaws.com/"+fileName;

        }

        // DB 에 저장.
        Review review = new Review();
        Optional<User> user = userRepository.findById(userId);

        if(!user.isPresent()){
            throw new RuntimeException();
        }
        review.user = user.get();

        Optional<Restaurant> restaurant =
                    restaurantRepository.findById(restaurantId);

        if(!restaurant.isPresent()){
            throw new RuntimeException();
        }

        review.restaurant = restaurant.get();

        Optional<Menu> menu = menuRepository.findById(menuId);

        if (!menu.isPresent()){
            throw new RuntimeException();
        }

        review.menu = menu.get();

        review.rating = Integer.valueOf(strRating);

        review.content = content;

        review.imageUrl = imageUrl;

        reviewRepository.save(review);

    }

}
