package com.simple.shopping.service.impl;

import com.simple.shopping.domain.Bill;
import com.simple.shopping.domain.OrderProduct;
import com.simple.shopping.domain.User;
import com.simple.shopping.repository.BillRepository;
import com.simple.shopping.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    BillRepository billRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Bill> getBillsByUserNo(Long userNo) {
        return billRepository.findBillsByUserNo(userNo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Bill> getBillsByPhone(String phone) {
        return billRepository.findBillsByPhone(phone);
    }

    @Override
    @Transactional(readOnly = true)
    public Bill getBillByNo(Long no) {
        return billRepository.findBillByNo(no);
    }

    @Override
    @Transactional
    public Bill addBill(Bill bill){
        Bill savedBill = billRepository.save(bill);
        return savedBill;
    };
}
