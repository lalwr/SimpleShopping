package com.simple.shopping.repository;

import com.simple.shopping.base.JpaQueryDslPredicateRepository;
import com.simple.shopping.domain.OrderProduct;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderProductRepository extends JpaQueryDslPredicateRepository<OrderProduct, Long> {

    @Query("SELECT sum(op.product.price*op.amount) FROM OrderProduct op JOIN op.bill WHERE op.bill.no = :billNo")
    public Long totalPrice(@Param("billNo") Long billNo);

    @Query("SELECT op FROM OrderProduct op JOIN op.bill JOIN op.product WHERE op.bill.no = :billNo AND op.product.no = :productNo")
    public OrderProduct findOrderProductByBillNoAndProductNo(@Param("billNo") Long billNo, @Param("productNo") Long productNo);

    @Query("SELECT op FROM OrderProduct op JOIN op.bill WHERE op.bill.no = :billNo")
    public List<OrderProduct> findOrderProductsByBillNo(@Param("billNo") Long billNo);
}
