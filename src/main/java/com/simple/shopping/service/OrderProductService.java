package com.simple.shopping.service;

import com.simple.shopping.domain.OrderProduct;

import java.util.List;

public interface OrderProductService {
    public List<OrderProduct> getOrderProductsbyBillNo(Long billNo);
    public OrderProduct getOrderProductByBillNoAndProductNo(Long billNo, Long productNo);
    public Long getTotalPrice(Long billNo);
    public OrderProduct addOrderProduct(OrderProduct orderProduct);

}
