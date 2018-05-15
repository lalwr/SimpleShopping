package com.simple.shopping.repository;

import com.simple.shopping.base.JpaQueryDslPredicateRepository;
import com.simple.shopping.domain.Product;
import com.simple.shopping.repositoryCustom.AdminRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;


public interface AdminRepository extends JpaQueryDslPredicateRepository<Product, Long>, AdminRepositoryCustom {
    @Query("SELECT p FROM Product p JOIN p.category c WHERE c.name=:category")
    Page<Product> findProductsByCondition(String category, Pageable pageable);
    Product findProductByNo(Long no);
    void deleteProductByNo(Long no);
}
