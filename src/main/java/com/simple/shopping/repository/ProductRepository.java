package com.simple.shopping.repository;

import com.simple.shopping.base.JpaQueryDslPredicateRepository;
import com.simple.shopping.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaQueryDslPredicateRepository<Product, Long> {

    @Query("SELECT COUNT(p) FROM Product p")
    public int countAll();

    @Query("SELECT COUNT(p) FROM Product p WHERE p.name like concat('%',:search,'%')")
    public int countAllByName(@Param("search") String search);

    @Query("SELECT COUNT(p) FROM Product p JOIN p.category c WHERE c.name = :category AND p.name like concat('%',:search,'%')")
    public int countAllByCategoryAndName(@Param("category") String category, @Param("search") String search);

    public Page<Product> findAllBy(Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.name like concat('%',:search,'%')")
    public Page<Product> findProductsByName(@Param("search") String search, Pageable pageable);

    @Query("SELECT p FROM Product p JOIN p.category c WHERE p.name like concat('%',:search,'%') AND c.name = :category")
    public Page<Product> findProductsByCategoryAndName(@Param("search") String search, @Param("category") String category, Pageable pageable);

    public Product findProductByNo(Long no);
}