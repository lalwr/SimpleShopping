package com.simple.shopping.service.impl;

import com.simple.shopping.domain.Cart;
import com.simple.shopping.domain.User;
import com.simple.shopping.repository.CartRepository;
import com.simple.shopping.repository.ProductRepository;
import com.simple.shopping.repository.UserRepository;
import com.simple.shopping.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Cart> getCartsbyUserNo(Long userNo) {
        return cartRepository.findCartsByUserNo(userNo);
    }

    @Override
    @Transactional(readOnly = true)
    public Cart getCartbyUserNoAndProductNo(Long userNo, Long productNo) {
        return cartRepository.findCartByUserNoAndProductNo(userNo, productNo);
    }

    @Override
    @Transactional
    public Cart addCart(String email, Long productNo, int amount) {
        Cart cart = cartRepository.findCartByUserNoAndProductNo(userRepository.findUserByEmail(email).getNo(), productNo);
        if(cart != null){
            cart.setAmount(cart.getAmount() + amount);
            Cart savedCart = cartRepository.save(cart);
            return savedCart;
        }else{
            cart = new Cart();
            cart.setUser(userRepository.findUserByEmail(email));
            cart.setProduct(productRepository.findProductByNo(productNo));
            cart.setAmount(amount);
            Cart savedCart = cartRepository.save(cart);
            return savedCart;
        }

    }

    @Override
    @Transactional
    public void updateCart(String email, Long productNo, int productAmount){
        Cart cart = cartRepository.findCartByUserNoAndProductNo(userRepository.findUserByEmail(email).getNo(), productNo);
        if(cart != null){
            cart.setAmount(productAmount);
            cartRepository.save(cart);
        }
    }

    @Override
    @Transactional
    public void deleteCart(String email, Long productNo){
        Cart cart = cartRepository.findCartByUserNoAndProductNo(userRepository.findUserByEmail(email).getNo(), productNo);
        if(cart != null){
            cartRepository.delete(cart);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Long getTotalPrice(Long userNo) {
        Long totalPrice =cartRepository.totalPrice(userNo);
        if(totalPrice != null){
            return totalPrice;
        }else{
            return new Long(0);
        }
    }

    @Override
    @Transactional
    public void cleanCart(){
        cartRepository.deleteAll();
    }
}
