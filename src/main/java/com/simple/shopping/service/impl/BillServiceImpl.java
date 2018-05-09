package com.simple.shopping.service.impl;

import com.simple.shopping.domain.Bill;
import com.simple.shopping.domain.OrderProduct;
import com.simple.shopping.domain.User;
import com.simple.shopping.repository.BillRepository;
import com.simple.shopping.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    BillRepository billRepository;

    @Override
    public List<Bill> getBillsByUserNo(Long userNo) {
        return billRepository.findBillsByUserNo(userNo);
    }

    @Override
    public Bill getBillByNo(Long no) {
        return billRepository.findBillByNo(no);
    }

    @Override
    public Bill addBill(Bill bill){
        Bill savedBill = billRepository.save(bill);
        return savedBill;
    };
}
