package com.block.jpareview.service;

import com.block.jpareview.dto.ProductListResponse;
import com.block.jpareview.dto.ProductResponse;
import com.block.jpareview.entity.Product;
import com.block.jpareview.repository.ProductRepository;
import com.block.jpareview.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ReviewRepository reviewRepository;

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
            return null;
        }
    }
}
