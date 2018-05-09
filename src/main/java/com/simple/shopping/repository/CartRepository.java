package com.simple.shopping.repository;

import com.simple.shopping.base.JpaQueryDslPredicateRepository;
import com.simple.shopping.domain.Cart;
import com.simple.shopping.domain.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaQueryDslPredicateRepository<Cart,Long> {

    @Query("SELECT c FROM Cart c JOIN c.user WHERE c.user.no = :userNo")
    public List<Cart> findCartsByUserNo(@Param("userNo") Long userNo);

    @Query("SELECT c FROM Cart c JOIN c.user u JOIN c.product p WHERE u.no = :userNo AND p.no = :productNo")
    public Cart findCartByUserNoAndProductNo(@Param("userNo") Long userNo, @Param("productNo") Long productNo);

    @Query("SELECT sum(c.product.price*c.amount) FROM Cart c JOIN c.user WHERE c.user.no = :userNo")
    public Long totalPrice(@Param("userNo") Long userNo);
}