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
    public List<Cart> getCartbyUserNo(Long userNo) {
        return cartRepository.findCartByUserNo(userNo);
    }

    @Override
    @Transactional
    public Cart addCart(String email, Long productNo, int amount) {
        List<Cart> carts = getCartbyUserNo(userRepository.findUserByEmail(email).getNo());
        for(Cart c : carts){
            if(c.getProduct() == productRepository.findProductByNo(productNo)){
                Cart cart = c;
                cart.setAmount(c.getAmount() + amount);
                Cart savedCart = cartRepository.save(cart);
                return savedCart;
            }
        }
        Cart cart = new Cart();
        cart.setUser(userRepository.findUserByEmail(email));
        cart.setProduct(productRepository.findProductByNo(productNo));
        cart.setAmount(amount);
        Cart savedCart = cartRepository.save(cart);
        return savedCart;
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

    public void updateCart(String email, String productName, int productAmount){
        List<Cart> carts = getCartbyUserNo(userRepository.findUserByEmail(email).getNo());
        for(Cart c : carts){
            if(c.getProduct().getName().equals(productName)){
                c.setAmount(productAmount);
                cartRepository.save(c);
            }
        }
    }

    public void deleteCart(String email, String productName){
        List<Cart> carts = getCartbyUserNo(userRepository.findUserByEmail(email).getNo());
        for(Cart c : carts){
            if(c.getProduct().getName().equals(productName)){
                cartRepository.delete(c);
            }
        }
    }

}
