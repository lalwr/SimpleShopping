package com.simple.shopping.service;

import com.simple.shopping.domain.Product;
import org.springframework.data.domain.Page;

public interface ProductService {
    Page<Product> getProducts(String category, int page);
    Page<Product> getProducts(int page);
    Product addProduct(Product product);
    Product getProductByNo(Long no);
}
