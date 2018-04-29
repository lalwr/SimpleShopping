package com.simple.shopping.repository;

import com.simple.shopping.base.JpaQueryDslPredicateRepository;
import com.simple.shopping.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaQueryDslPredicateRepository<Product, Long> {

    public Page<Product> findAllBy(Pageable pageable);
    public Page<Product> findProductsByCategory_No(@Param("category_no") int categoryNo, Pageable pageable);
    public Product findProductByNo(Long no);
}
