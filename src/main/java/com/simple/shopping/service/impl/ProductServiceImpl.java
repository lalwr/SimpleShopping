package com.simple.shopping.service.impl;

import com.simple.shopping.PageManager;
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
    public int countAllByName(String search) {
        return productRepository.countAllByName(search);
    }

    @Override
    @Transactional(readOnly = true)
    public int countAllByCategoryAndName(String search, String category) {
        return productRepository.countAllByCategoryAndName(category, search);
    }

    @Override
    @Transactional(readOnly = true)
    public int countAll() {
        return productRepository.countAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Product> getProducts(String search, String category, int page) {
        Page<Product> products;
        PageRequest pageRequest = PageRequest.of(page - 1, PageManager.maxProduct,new Sort(Sort.Direction.ASC, "no"));
        if("All".equals(category) && (search == null || "".equals(search))) {
            products = productRepository.findAllBy(pageRequest);
        }else if("All".equals(category)){
            products = productRepository.findProductsByName(search,pageRequest);
        }else{
            products = productRepository.findProductsByCategoryAndName(search, category,pageRequest);
        }
        return products;
    }

    @Override
    @Transactional
    public Product getProductByNo(Long no) {
        return productRepository.findProductByNo(no);
    }
}