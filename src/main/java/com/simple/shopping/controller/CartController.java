package com.simple.shopping.controller;

import com.simple.shopping.domain.Cart;
import com.simple.shopping.service.CartService;
import com.simple.shopping.service.ProductService;
import com.simple.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @GetMapping
    public String cartList(Principal principal,
                           ModelMap modelMap){
        Long userNo = userService.getUserByEmail(principal.getName()).getNo();
        List<Cart> carts = cartService.getCartbyUserNo(userNo);
        modelMap.addAttribute("carts", carts);

        Long totalPrice = cartService.getTotalPrice(userNo);
        modelMap.addAttribute("totalPrice", totalPrice);
        return "cart/list";
    }

    @PostMapping
    public String addCart(Principal principal,
                          @RequestParam("productNo") Long productNo,
                          @RequestParam("amount") int amount){
        Cart cart = cartService.addCart(principal.getName(), productNo, amount);
        return "redirect:/cart";
    }

    @PutMapping
    public String updateCart(Principal principal,
                             @RequestParam(name = "productName") String productName,
                             @RequestParam(name = "productAmount") int productAmount){
        cartService.updateCart(principal.getName(), productName, productAmount);
        return "redirect:/cart";
    }

    @DeleteMapping
    public String deleteCart(Principal principal,
                             @RequestParam(name = "productName") String productName){
        cartService.deleteCart(principal.getName(), productName);
        return "redirect:/cart";
    }
}