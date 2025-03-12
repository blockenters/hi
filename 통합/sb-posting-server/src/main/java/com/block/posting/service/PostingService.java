package com.block.posting.service;

import com.block.posting.config.JwtConfig;
import com.block.posting.dao.PostingDAO;
import com.block.posting.dto.PostingListRes;
import com.block.posting.entity.Posting;
import com.block.posting.util.UniqueFileNameGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.List;

@Service
public class PostingService {

    @Autowired
    JwtConfig jwtConfig;

    @Value("${cloud.aws.s3.bucket}")
    String bucketName;

    @Autowired
    S3Client s3Client;

    @Autowired
    PostingDAO postingDAO;

    public void createPosting(String token, MultipartFile image, String content){
       long userId = Long.parseLong( jwtConfig.getTokenClaims(token.substring(7)).getSubject());

       String fileName  = UniqueFileNameGenerator.generateUniqueFileName(userId, ".jpg");

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .contentLength(image.getSize())
                .contentType(image.getContentType())
                .acl(ObjectCannedACL.PUBLIC_READ)
                .build();
        try {
            s3Client.putObject(putObjectRequest,
                    RequestBody.fromInputStream(image.getInputStream(), image.getSize()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String imgUrl = "https://" + bucketName + ".s3.amazonaws.com/" + fileName;

        postingDAO.createPosting(userId, imgUrl, content);

    }

    public PostingListRes getPostingList(String token, int page, int size) {
        long userId = Long.parseLong( jwtConfig.getTokenClaims(token.substring(7)).getSubject());

        int offset = (page - 1)* size;

        List<Posting> postingList = postingDAO.getPostingList(userId, offset, size);

        PostingListRes res = new PostingListRes(postingList, postingList.size());

        return res;
    }
}
