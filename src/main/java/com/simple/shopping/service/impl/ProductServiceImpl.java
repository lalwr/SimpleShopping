package com.simple.shopping.service.impl;

import com.simple.shopping.domain.Product;
import com.simple.shopping.repository.ProductRepository;
import com.simple.shopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<Product> getProducts(int page) {
        return getProducts(null,page);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Product> getProducts(String category, int page) {
        Page<Product> products = null;
        PageRequest pageRequest = PageRequest.of(page - 1, 6,new Sort(Sort.Direction.ASC, "no"));
        if(("".equals(category)||category == null)) {
            products = productRepository.findAllBy(pageRequest);
        }
//        }else if("Linux".equals(category)){
//            products = productRepository.findProductsByCategory_No(1, pageRequest);
//        }else if("Programming".equals(category)){
//            products = productRepository.findProductsByCategory_No(2, pageRequest);
//        }else if("Computer".equals(category)){
//            products = productRepository.findProductsByCategory_No(3, pageRequest);
//        }else if("ETC".equals(category)){
//            products = productRepository.findProductsByCategory_No(4, pageRequest);
//        }

        return products;
    }

    @Override
    @Transactional
    public Product addProduct(Product product) {
        return null;
    }

    @Override
    @Transactional
    public Product getProductByNo(Long no) {
        return productRepository.findProductByNo(no);
    }
}
