package com.simple.shopping.controller;

import com.simple.shopping.domain.Cart;
import com.simple.shopping.service.CartService;
import com.simple.shopping.service.ProductService;
import com.simple.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
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
                           HttpSession session,
                           ModelMap modelMap){
        if(principal == null){
            List<Cart> carts = (List<Cart>)session.getAttribute("carts");
            modelMap.addAttribute("carts", carts);

            int totalPrice = 0;
            if(carts != null) {
                for (Cart cart : carts) {
                    totalPrice += cart.getProduct().getPrice() * cart.getAmount();
                }
            }
            modelMap.addAttribute("totalPrice", totalPrice);
            return "cart/list";
        }else {
            List<Cart> sessionCart = (List<Cart>)session.getAttribute("carts");
            Long userNo = userService.getUserByEmail(principal.getName()).getNo();
            if(sessionCart != null && sessionCart.size() >0) {
                for (Cart cart : sessionCart) {
                    Long productNo = cart.getProduct().getNo();
                    int productAmount = cart.getAmount();
                    cartService.addCart(principal.getName(), productNo, productAmount);
                }
            }
            List<Cart> carts = cartService.getCartsbyUserNo(userNo);
            modelMap.addAttribute("carts", carts);

            Long totalPrice = cartService.getTotalPrice(userNo);
            modelMap.addAttribute("totalPrice", totalPrice);
            return "cart/list";
        }
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
                          @RequestParam("productAmount") int productAmount,
                          HttpServletRequest request){
        HttpSession session = request.getSession();
        List<Cart> carts = (List<Cart>)session.getAttribute("carts");
        if(carts == null){
            carts = new ArrayList<>();
            session.setAttribute("carts", carts);
            carts = (List<Cart>)session.getAttribute("carts");
        }

        if(principal == null){

            if(  carts.size() == 0){
                Cart cart = new Cart();
                int productStock = productService.getProductByNo(productNo).getAmount();
                if (productAmount <= productStock && productStock > 0) {
                    cart.setProduct(productService.getProductByNo(productNo));
                    cart.setAmount(productAmount);
                    carts.add(cart);
                }
            }else{
                if(carts != null) {
                    for (Cart cart : carts) {
                        if (cart.getProduct().getNo().equals(productNo)) {
                            int productStock = productService.getProductByNo(productNo).getAmount();
                            if (productAmount + cart.getAmount() <= productStock && productStock > 0) {
                                cart.setProduct(productService.getProductByNo(productNo));
                                cart.setAmount(cart.getAmount() + productAmount);
                            }
                        } else {
                            int productStock = productService.getProductByNo(productNo).getAmount();
                            if (productAmount <= productStock && productStock > 0) {
                                cart.setProduct(productService.getProductByNo(productNo));
                                cart.setAmount(productAmount);
                                carts.add(cart);
                            }
                        }
                    }
                }
            }
        }else {
            int productStock = productService.getProductByNo(productNo).getAmount();
            if (productAmount <= productStock && productStock > 0) {
                cartService.addCart(principal.getName(), productNo, productAmount);
            }
        }
        return "redirect:/cart";
    }

    @PutMapping
    public String updateCart(Principal principal,
                             HttpSession session,
                             @RequestParam(name = "productNo") Long productNo,
                             @RequestParam(name = "productAmount") int productAmount){
        if(principal == null){
            int productStock = productService.getProductByNo(productNo).getAmount();
            List<Cart> carts = (List<Cart>)session.getAttribute("carts");
            if (productAmount <= productStock) {
                for(Cart cart : carts){
                    if(cart.getProduct().getNo() == productNo){
                        cart.setAmount(productAmount);
                    }
                }
            }
            return "redirect:/cart";
        }else {
            int productStock = productService.getProductByNo(productNo).getAmount();
            if (productAmount <= productStock) {
                cartService.updateCart(principal.getName(), productNo, productAmount);
            }
            return "redirect:/cart";
        }
    }

    @DeleteMapping
    public String deleteCart(Principal principal,
                             HttpSession session,
                             @RequestParam(name = "productNo") Long productNo){
        if(principal == null){
            List<Cart> carts = (List<Cart>)session.getAttribute("carts");
            for (Cart cart : carts) {
                if (cart.getProduct().getNo() == productNo) {
                    carts.remove(cart);
                    return "redirect:/cart";
                }
            }
            return "redirect:/cart";
        }else {
            cartService.deleteCart(principal.getName(), productNo);
            return "redirect:/cart";
        }
    }
}