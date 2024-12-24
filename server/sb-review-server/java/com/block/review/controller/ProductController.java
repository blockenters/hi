package com.block.review.controller;

import com.block.review.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/api/products")
    getAllProducts(@RequestParam int page, @RequestParam int size){

        productService.getAllProducts(page, size);

    }

}
