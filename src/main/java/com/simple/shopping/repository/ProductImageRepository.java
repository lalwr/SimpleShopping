package com.simple.shopping.repository;

import com.simple.shopping.base.JpaQueryDslPredicateRepository;
import com.simple.shopping.domain.ProductImage;

public interface ProductImageRepository extends JpaQueryDslPredicateRepository<ProductImage, Long> {
    ProductImage findProductImageByNo(Long no);
}
