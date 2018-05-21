package com.simple.shopping.repositoryCustom.Impl;

import com.querydsl.jpa.JPQLQuery;
import com.simple.shopping.domain.Product;
import com.simple.shopping.domain.QProduct;
import com.simple.shopping.repositoryCustom.AdminRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;


public class AdminRepositoryCustomImpl extends QuerydslRepositorySupport implements AdminRepositoryCustom {

    public AdminRepositoryCustomImpl(){
        super(Product.class);
    }

    /*
     * Paramf
     * searchType: categoryName
     * searchStr:
     * Return
     * Page<Product>
     */
    @Override
    public Page<Product> getProductList(Long categoryNo, String searchType, String searchStr, Pageable pageable){
        QProduct product = QProduct.product;
        JPQLQuery query = from(product);

        if(searchType != null){
            switch(searchType){
                case "productName":
                    query.where(product.name.contains(searchStr));
                    break;
                default:
                    break;
            }
        }
        if(categoryNo != null && categoryNo != 0L && categoryNo!=1){
            query.where(product.category.no.eq(categoryNo));
        }


        List<Product> productList = getQuerydsl().applyPagination(pageable, query).fetch();
        long totalCount = query.fetchCount();

        return new PageImpl<>(productList, pageable, totalCount);
    }

}
