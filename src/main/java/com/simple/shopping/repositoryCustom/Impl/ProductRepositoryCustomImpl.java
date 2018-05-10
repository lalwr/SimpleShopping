package com.simple.shopping.repositoryCustom.Impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.simple.shopping.domain.Product;
import com.simple.shopping.domain.QProduct;
import com.simple.shopping.repositoryCustom.ProductRepositoryCustom;

public class ProductRepositoryCustomImpl implements ProductRepositoryCustom{
    @Override
    public Product getProduct(String product) {
        QProduct qProduct = QProduct.product;
//        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        return null;
    }
}
