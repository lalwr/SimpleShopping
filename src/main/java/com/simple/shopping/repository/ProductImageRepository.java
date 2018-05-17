package com.simple.shopping.repository;

import com.simple.shopping.base.JpaQueryDslPredicateRepository;
import com.simple.shopping.domain.ProductImage;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductImageRepository extends JpaQueryDslPredicateRepository<ProductImage, Long> {
    ProductImage findProductImageByNo(Long no);
    @Modifying @Query(value="DELETE FROM ProductImage pi where pi.no=:piNo")
    void deleteProductImageByNo(@Param("piNo") Long no);
}
