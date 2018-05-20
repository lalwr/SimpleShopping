package com.simple.shopping.repositoryCustom.Impl;

import com.querydsl.jpa.JPQLQuery;
import com.simple.shopping.domain.Bill;
import com.simple.shopping.domain.QBill;
import com.simple.shopping.repositoryCustom.BillRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.*;

public class BillRepositoryCustomImpl extends QuerydslRepositorySupport implements BillRepositoryCustom {
    public BillRepositoryCustomImpl(){
        super(Bill.class);
    }

    @Override
    public Page<Bill> getOrderList(String searchType, String searchStr, Pageable pageable) {
        QBill order = QBill.bill;
        JPQLQuery query = from(order);

        if(searchType != null){
            switch(searchType){
                case "userName":
                    query.where(order.user.name.contains(searchStr));
                    break;
                default:
                    break;
            }
        }

        List<Bill> billList = getQuerydsl().applyPagination(pageable, query).fetch();

        long totalCount = query.fetchCount();

        return new PageImpl<>(billList, pageable, totalCount);
    }
}
