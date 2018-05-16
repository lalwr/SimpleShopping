package com.simple.shopping.service.impl;

import com.simple.shopping.PageManager;
import com.simple.shopping.domain.Product;
import com.simple.shopping.domain.ProductImage;
import com.simple.shopping.dto.Pagination;
import com.simple.shopping.repository.AdminRepository;
import com.simple.shopping.service.AdminService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Service
public class AdminServiceImpl implements AdminService {
    AdminRepository adminRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository){
        this.adminRepository = adminRepository;
    }

    @Override
    @Transactional
    public Product addProduct(Product product) {
        return (Product)adminRepository.save(product);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Product> getProductList(Pagination pagination, Long categoryNo){
        Pageable pageable = PageRequest.of(pagination.getCurPage()-1, pagination.getCountPerPage(), new Sort(Sort.Direction.DESC, "no"));
        Page<Product> productList = adminRepository.getProductList(categoryNo, pagination.getSearchType(), pagination.getSearchStr(), pageable);

        return productList;
    }

    @Override
    @Transactional
    public Product findProduct(Long no) {
        return adminRepository.findProductByNo(no);
    }

    @Override
    @Transactional
    public void deleteProductByNo(Long no) {
        adminRepository.deleteProductByNo(no);
    }

    @Override
    @Transactional
    public void deleteProductList(List<Product> productList) {
        for(Product product: productList){
            deleteProductByNo(product.getNo());
        }
    }

}
