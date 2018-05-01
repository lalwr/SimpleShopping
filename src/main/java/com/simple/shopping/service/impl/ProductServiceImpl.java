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

    ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public int countAllByName(String search) {
        return productRepository.countAllByName(search);
    }

    @Override
    @Transactional
    public int countAllByCategoryAndName(String search, String category) {
        return productRepository.countAllByCategoryAndName(search, category);
    }

    @Override
    @Transactional
    public int countAll() {
        return productRepository.countAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Product> getProducts(int page) {
        return getProducts(null, null,page);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Product> getProducts(String search, String category, int page) {
        Page<Product> products=null;
        PageRequest pageRequest = PageRequest.of(page - 1, 6,new Sort(Sort.Direction.ASC, "no"));
        if(category == null){
            category="All";
        }
        if("All".equals(category) && (search == null || "".equals(search))) {
            products = productRepository.findAllBy(pageRequest);
        }else if("All".equals(category) && (search != null || !"".equals(search))){
            products = productRepository.findProductsByName(search,pageRequest);
        }else{
            switch (category){
                case "Linux":
                    products = productRepository.findProductsByCategoryAndName(search, "Linux",pageRequest);
                    break;
                case "Programming":
                    products = productRepository.findProductsByCategoryAndName(search, "Programming",pageRequest);
                    break;
                case "Computer":
                    products = productRepository.findProductsByCategoryAndName(search, "Computer",pageRequest);
                    break;
                case "ETC":
                    products = productRepository.findProductsByCategoryAndName(search, "ETC",pageRequest);
            }
        }


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
