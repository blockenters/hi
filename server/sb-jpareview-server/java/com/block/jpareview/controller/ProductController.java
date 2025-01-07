package com.block.jpareview.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @GetMapping("/api/products")
    getAllProducts(@RequestParam int page,
                   @RequestParam int size,
                   @RequestParam(required = false) String category){

        = productService.getAllProducts(page, size, category);

    }
}
