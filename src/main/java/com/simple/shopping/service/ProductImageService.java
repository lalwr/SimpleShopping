package com.simple.shopping.service;

import com.simple.shopping.domain.ProductImage;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ProductImageService {
    ProductImage saveProductImage(MultipartFile multipartFile);
    void downProductImage(HttpServletRequest request, HttpServletResponse response, ProductImage productImage);
    ProductImage findProductImageByNo(Long No);
    void deleteProductImageFile(ProductImage productImage);
    void deleteProductImageByNo(Long no);
}
