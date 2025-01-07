package com.block.jpareview.service;

import com.block.jpareview.dto.*;
import com.block.jpareview.entity.Product;
import com.block.jpareview.entity.Review;
import com.block.jpareview.repository.ProductRepository;
import com.block.jpareview.repository.ReviewRepository;
import com.block.jpareview.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ReviewRepository reviewRepository;


    public ProductDetailReviewResponse getProduct(long productId){
        Optional<Product> product = productRepository.findById(productId);
        if(product.isPresent() == false){
            throw new RuntimeException();
        }
        ProductDetailResponse productDetailResponse =
                new ProductDetailResponse();
        productDetailResponse.id = product.get().id;
        productDetailResponse.name = product.get().name;
        productDetailResponse.description = product.get().description;
        productDetailResponse.price = product.get().price;
        productDetailResponse.stockQuantity = product.get().stockQuantity;
        productDetailResponse.category = product.get().category;
        productDetailResponse.createdAt = product.get().createdAt.toString();

        productDetailResponse.averageRating = reviewRepository.findAverageRatingByProductId(productId);
        productDetailResponse.reviewCount = reviewRepository.countByProductId(productId);

        // 최근 리뷰 5개 가져오기
        List<Review> reviewList =
                reviewRepository.findTop5ByProductIdOrderByCreatedAtDesc(productId);

        // entity를 dto로 저장.
        ArrayList<ReviewResponse> reviewResponseList =
                                            new ArrayList<>();
        for( Review review : reviewList){
            ReviewResponse reviewResponse = new ReviewResponse();
            reviewResponse.id = review.id;
            reviewResponse.nickname = review.user.nickname;
            reviewResponse.rating = review.rating;
            reviewResponse.content = review.content;
            reviewResponse.createdAt = review.createdAt.toString();
            reviewResponse.updatedAt = review.updatedAt.toString();
            reviewResponseList.add(reviewResponse);
        }
        // 전체 데이터를 DTO로 만든다.
        ProductDetailReviewResponse response =
                                new ProductDetailReviewResponse();
        response.product = productDetailResponse;
        response.recentReviews = reviewResponseList;
        return response;
    }


    public ProductListResponse getAllProducts(int page, int size, String category){

        if(category == null){
            PageRequest pageRequest = PageRequest.of(page-1, size);
            Page<Product> productPage = productRepository.findAll(pageRequest);

            ArrayList<ProductResponse> productList = new ArrayList<>();
            for( Product product  : productPage ){
                double averageRating = reviewRepository.findAverageRatingByProductId(product.id);
                int reviewCount = reviewRepository.countByProductId(product.id);

                // entity 를 DTO 에 저장한다.
                ProductResponse productResponse = new ProductResponse();
                productResponse.id = product.id;
                productResponse.name = product.name;
                productResponse.category = product.category;
                productResponse.price = product.price;
                productResponse.stockQuantity = product.stockQuantity;
                productResponse.averageRating = averageRating;
                productResponse.reviewCount = reviewCount;
                // 리스트에 저장한다.
                productList.add(productResponse);
            }
            ProductListResponse productListResponse =
                    new ProductListResponse();
            productListResponse.content = productList;
            productListResponse.page = page;
            productListResponse.size = size;
            productListResponse.totalElements = productPage.getTotalElements();
            productListResponse.totalPages = productPage.getTotalPages();

            return productListResponse;

        }else{
            PageRequest pageRequest = PageRequest.of(page-1, size);
            Page<Product> productPage =
                    productRepository.findAllByCategory(category, pageRequest);

            ArrayList<ProductResponse> productList = new ArrayList<>();
            for( Product product  : productPage ){
                double averageRating = reviewRepository.findAverageRatingByProductId(product.id);
                int reviewCount = reviewRepository.countByProductId(product.id);

                // entity 를 DTO 에 저장한다.
                ProductResponse productResponse = new ProductResponse();
                productResponse.id = product.id;
                productResponse.name = product.name;
                productResponse.category = product.category;
                productResponse.price = product.price;
                productResponse.stockQuantity = product.stockQuantity;
                productResponse.averageRating = averageRating;
                productResponse.reviewCount = reviewCount;
                // 리스트에 저장한다.
                productList.add(productResponse);
            }
            ProductListResponse productListResponse =
                    new ProductListResponse();
            productListResponse.content = productList;
            productListResponse.page = page;
            productListResponse.size = size;
            productListResponse.totalElements = productPage.getTotalElements();
            productListResponse.totalPages = productPage.getTotalPages();

            return productListResponse;
        }
    }
}
