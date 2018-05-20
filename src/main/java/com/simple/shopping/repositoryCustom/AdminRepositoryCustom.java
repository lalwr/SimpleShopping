package com.simple.shopping.repositoryCustom;

import com.simple.shopping.domain.Bill;
import com.simple.shopping.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminRepositoryCustom {
    Page<Product> getProductList(Long categoryNo, String searchType, String searchStr, Pageable pageable);
}
