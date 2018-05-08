package com.simple.shopping.service;

import com.simple.shopping.domain.Cart;
import com.simple.shopping.domain.User;

import java.util.List;

public interface CartService {
    public List<Cart> getCartbyUserNo(Long userNo);
    public Long getTotalPrice(Long userNo);
    public Cart addCart(String email, Long productNo, int amount);
    public void updateCart(String email, String productName, int productAmount);
    public void deleteCart(String email, String productName);
}
