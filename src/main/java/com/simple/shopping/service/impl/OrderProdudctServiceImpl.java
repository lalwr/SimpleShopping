package com.simple.shopping.service.impl;

import com.simple.shopping.domain.OrderProduct;
import com.simple.shopping.repository.OrderProductRepository;
import com.simple.shopping.service.OrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderProdudctServiceImpl implements OrderProductService {

    @Autowired
    OrderProductRepository orderProductRepository;

    @Override
    @Transactional(readOnly = true)
    public List<OrderProduct> getOrderProductsbyBillNo(Long billNo) {
        return orderProductRepository.findOrderProductsByBillNo(billNo);
    }

    @Override
    @Transactional(readOnly = true)
    public OrderProduct getOrderProductByBillNoAndProductNo(Long billNo, Long productNo) {
        return orderProductRepository.findOrderProductByBillNoAndProductNo(billNo, productNo);
    }

    @Override
    @Transactional(readOnly = true)
    public Long getTotalPrice(Long billNo) {
        Long totalPrice =orderProductRepository.totalPrice(billNo);
        if(totalPrice != null){
            return totalPrice;
        }else{
            return new Long(0);
        }
    }

    @Override
    @Transactional
    public OrderProduct addOrderProduct(OrderProduct orderProduct){
        OrderProduct savedOrderProduct = orderProductRepository.save(orderProduct);
        return savedOrderProduct;
    }

}
