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

    @Query("SELECT COUNT(p) FROM Product p WHERE p.category.name = :category AND p.name = :search")
    public int countAllByCategoryAndName(@Param("category") String category, @Param("search") String search);

    public Page<Product> findAllBy(Pageable pageable);

//    SELECT * FROM PRODUCT JOIN CATEGORY ON PRODUCT.CATEGORY_NO = CATEGORY.NO WHERE CATEGORY.NAME = 'Linux' AND PRODUCT.NAME ='sticker1';
//    @Query(value = "SELECT * FROM PRODUCT p JOIN CATEGORY c ON p.CATEGORY_NO = c.NO WHERE (p.NAME = :name AND c.NAME = :category)", nativeQuery = true)
    @Query(value = "SELECT * FROM PRODUCT p JOIN CATEGORY c ON p.category_no = c.no WHERE (p.name like concat('%',:search,'%') AND c.name = :category)", nativeQuery = true)
    public Page<Product> findProductsByCategoryAndName(@Param("search") String search, @Param("category") String category, Pageable pageable);


    @Query(value = "SELECT * FROM PRODUCT p WHERE p.name like concat('%',:search,'%')", nativeQuery = true)
    public Page<Product> findProductsByName(@Param("search") String search, Pageable pageable);

    public Product findProductByNo(Long no);
}
