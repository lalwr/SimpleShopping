package com.simple.shopping.service;

import com.simple.shopping.domain.Cart;
import com.simple.shopping.domain.User;

import java.util.List;

public interface CartService {
    public List<Cart> getCartsbyUserNo(Long userNo);
    public Cart getCartbyUserNoAndProductNo(Long userNo, Long productNo);
    public Long getTotalPrice(Long userNo);
    public Cart addCart(String email, Long productNo, int amount);
    public void updateCart(String email, Long productNo, int productAmount);
    public void deleteCart(String email, Long productNo);
}
