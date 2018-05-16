package com.simple.shopping.dto;

import com.simple.shopping.domain.Product;

import java.util.List;

public class ProductDto {
    List<Product> productList;

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
