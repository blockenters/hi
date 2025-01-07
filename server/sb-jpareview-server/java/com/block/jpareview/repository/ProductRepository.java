package com.block.jpareview.repository;

import com.block.jpareview.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    public Page<Product>  findAllByCategory(String category,
                                            Pageable pageable);
}
