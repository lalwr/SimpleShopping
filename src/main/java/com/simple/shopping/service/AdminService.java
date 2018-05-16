package com.simple.shopping.service;

import com.simple.shopping.domain.Category;
import com.simple.shopping.domain.Product;
import com.simple.shopping.domain.ProductImage;
import com.simple.shopping.dto.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

public interface AdminService {
    Product addProduct(Product product);
    Page<Product> getProductList(Pagination pagination, Long categoryNo);
    Product findProduct(Long no);
    void deleteProductByNo(Long no);
    void deleteProductList(List<Product> productList);

}
