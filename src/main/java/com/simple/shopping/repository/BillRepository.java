package com.simple.shopping.repository;

import com.simple.shopping.base.JpaQueryDslPredicateRepository;
import com.simple.shopping.domain.Bill;
import com.simple.shopping.repositoryCustom.BillRepositoryCustom;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BillRepository extends JpaQueryDslPredicateRepository<Bill, Long>, BillRepositoryCustom {

    @Query("SELECT b From Bill b JOIN b.user WHERE b.user.no = :userNo")
    public List<Bill> findBillsByUserNo(@Param("userNo") Long userNo);

    @Query("SELECT b FROM Bill b where b.phone = :phone")
    public List<Bill> findBillsByPhone(@Param("phone") String phone);

    @Query("SELECT b FROM Bill b WHERE b.no = :no")
    public Bill findBillByNo(@Param("no") Long no);

}
