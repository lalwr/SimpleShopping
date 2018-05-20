package com.simple.shopping.service.impl;

import com.simple.shopping.domain.Bill;
import com.simple.shopping.domain.Product;
import com.simple.shopping.dto.Pagination;
import com.simple.shopping.repository.AdminRepository;
import com.simple.shopping.repository.BillRepository;
import com.simple.shopping.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class AdminServiceImpl implements AdminService {
    AdminRepository adminRepository;
    BillRepository billRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository, BillRepository billRepository){
        this.adminRepository = adminRepository;
        this.billRepository = billRepository;
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
    @Transactional(readOnly = true)
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

    @Override
    @Transactional
    public void updateProduct(Product savedProduct, Product inputProduct) {
        savedProduct.setProductRegisterFormValue(inputProduct);
    }
    ////////////////////////////////////////////

    @Override
    @Transactional
    public Page<Bill> getBillList(Pagination pagination){
        Pageable pageable = PageRequest.of(pagination.getCurPage()-1, pagination.getCountPerPage(), new Sort(Sort.Direction.DESC,"no"));
        return billRepository.getOrderList(pagination.getSearchType(), pagination.getSearchStr(),pageable);
    }

    @Override
    @Transactional
    public Bill getBill(Long no) {
        return billRepository.findBillByNo(no);
    }

    @Override
    @Transactional
    public void updateBillStatus(Bill bill, String status) {
        bill.setStatus(status);
    }
}
