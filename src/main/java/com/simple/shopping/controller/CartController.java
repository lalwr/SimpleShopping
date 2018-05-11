package com.simple.shopping.controller;

import com.simple.shopping.domain.Cart;
import com.simple.shopping.domain.Product;
import com.simple.shopping.service.CartService;
import com.simple.shopping.service.ProductService;
import com.simple.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

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
        List<Cart> carts = cartService.getCartsbyUserNo(userNo);
        modelMap.addAttribute("carts", carts);

        Long totalPrice = cartService.getTotalPrice(userNo);
        modelMap.addAttribute("totalPrice", totalPrice);
        return "cart/list";
    }

    @GetMapping("/productStock/{productNo}")
    @ResponseBody
    public Long productStock(@PathVariable Long productNo){
        Long productStock = new Long(productService.getProductByNo(productNo).getAmount());
        return productStock;
    }

    @GetMapping("/orderable")
    public String orderable(@RequestParam(name = "ordercheck") boolean check){
        if(check){
            return "redirect:/order/carts";
        }else{
            return "redirect:/cart";
        }
    }

    @PostMapping
    public String addCart(Principal principal,
                          @RequestParam("productNo") Long productNo,
                          @RequestParam("productAmount") int productAmount){
        int productStock = productService.getProductByNo(productNo).getAmount();
        if (productAmount <= productStock && productStock > 0) {
            cartService.addCart(principal.getName(), productNo, productAmount);
        }
        return "redirect:/cart";
    }

    @PutMapping
    public String updateCart(Principal principal,
                             @RequestParam(name = "productNo") Long productNo,
                             @RequestParam(name = "productAmount") int productAmount){
        int productStock = productService.getProductByNo(productNo).getAmount();
        if(productAmount <= productStock) {
            cartService.updateCart(principal.getName(), productNo, productAmount);
        }
        return "redirect:/cart";
    }

    @DeleteMapping
    public String deleteCart(Principal principal,
                             @RequestParam(name = "productNo") Long productNo){
        cartService.deleteCart(principal.getName(), productNo);
        return "redirect:/cart";
    }
}