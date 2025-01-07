package com.block.jpareview.controller;

import com.block.jpareview.dto.ProductDetailReviewResponse;
import com.block.jpareview.dto.ProductListResponse;
import com.block.jpareview.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/api/products/{productId}")
    public ResponseEntity<ProductDetailReviewResponse> getProduct(@PathVariable long productId){

        ProductDetailReviewResponse response =
                            productService.getProduct(productId);
        return ResponseEntity.status(200).body(response);
    }




    @GetMapping("/api/products")
    public ResponseEntity<ProductListResponse> getAllProducts(@RequestParam int page,
                                                       @RequestParam int size,
                                                       @RequestParam(required = false) String category){

        ProductListResponse productListResponse =
                productService.getAllProducts(page, size, category);

        return ResponseEntity.status(200).body(productListResponse);

    }
}
