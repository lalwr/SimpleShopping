package com.simple.shopping.service;

import com.simple.shopping.domain.Category;
import com.simple.shopping.domain.Product;
import com.simple.shopping.domain.ProductImage;
import com.simple.shopping.dto.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface AdminService {
    Product addProduct(Product product);
    Page<Product> getProductList(Pagination pagination, String searchType, String searchStr);
    Product findProduct(Long no);
    void deleteProductByNo(Long no);
    void deleteProductList(List<Product> productList);
    ProductImage saveProductImage(MultipartFile multipartFile);
}
