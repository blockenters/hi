package com.block.review.service;

import com.block.review.dao.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    ProductDAO productDAO;

    getAllProducts(int page, int size){
            productDAO.getAllProducts(page, size);
    }

}
