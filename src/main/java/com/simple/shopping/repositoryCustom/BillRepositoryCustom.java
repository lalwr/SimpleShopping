package com.simple.shopping.repositoryCustom;

import com.simple.shopping.domain.Bill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BillRepositoryCustom {
    Page<Bill> getOrderList(String searchType, String searchStr, Pageable pageable);
}
