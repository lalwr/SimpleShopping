package com.simple.shopping.repository;

import com.simple.shopping.base.JpaQueryDslPredicateRepository;
import com.simple.shopping.domain.Product;
import com.simple.shopping.repositoryCustom.AdminRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;


public interface AdminRepository extends JpaQueryDslPredicateRepository<Product, Long>, AdminRepositoryCustom {
    Product findProductByNo(Long no);
    void deleteProductByNo(Long no);
}
