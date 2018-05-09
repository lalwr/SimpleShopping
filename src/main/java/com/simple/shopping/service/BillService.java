package com.simple.shopping.service;

import com.simple.shopping.domain.Bill;
import com.simple.shopping.domain.OrderProduct;
import com.simple.shopping.domain.User;

import java.time.LocalDateTime;
import java.util.List;

public interface BillService {
    List<Bill> getBillsByUserNo(Long userNo);
    Bill getBillByNo(Long no);
    Bill addBill(Bill bill);
}
//Bill : name, address, phone, regdate, user, List<OrderProduct>