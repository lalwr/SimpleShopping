package com.simple.shopping.service.impl;

import com.simple.shopping.domain.Product;
import com.simple.shopping.dto.Pagination;
import com.simple.shopping.repository.AdminRepository;
import com.simple.shopping.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
    @Transactional
    public Page<Product> getProductList(int page, String searchType, String searchStr){
//        Pageable pageable = PageRequest.of(pagination.getCurPage()-1, pagination.getCountPerPage(), new Sort(Sort.Direction.DESC, "no"));
//        Page<Product> productList = adminRepository.findAllByCategoryName("Linux", pageable);

        return null;
    }
}
