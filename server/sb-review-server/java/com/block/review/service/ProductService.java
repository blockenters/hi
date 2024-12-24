package com.block.review.service;

import com.block.review.dao.ProductDAO;
import com.block.review.dto.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductDAO productDAO;

    getAllProducts(int page, int size){
        List<ProductResponse> productList = productDAO.getAllProducts(page, size);
        int totalElements = productDAO.getTotalElements();

        int totalPages = (int)Math.ceil(totalElements / (double)size);
        int currentPage = page;
        int pageSize = size;
    }

}
