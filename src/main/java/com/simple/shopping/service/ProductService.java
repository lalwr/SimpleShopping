package com.simple.shopping.service;

import com.simple.shopping.domain.Product;
import org.springframework.data.domain.Page;

public interface ProductService {
    Page<Product> getProducts(String search, String category, int page);
    Product getProductByNo(Long no);
    int countAll();
    int countAllByName(String search);
    int countAllByCategoryAndName(String search, String category);
}
