package com.simple.shopping.service;

import com.simple.shopping.domain.Category;
import com.simple.shopping.domain.Product;
import com.simple.shopping.dto.Pagination;
import org.springframework.data.domain.Page;

public interface AdminService {
    Product addProduct(Product product);
    Page<Product> getProductList(int page, String searchType, String searchStr);
}
