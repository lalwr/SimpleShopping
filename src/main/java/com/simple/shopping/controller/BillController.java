package com.simple.shopping.controller;

import com.simple.shopping.domain.Bill;
import com.simple.shopping.domain.Cart;
import com.simple.shopping.domain.OrderProduct;
import com.simple.shopping.domain.User;
import com.simple.shopping.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/order")
public class BillController {

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    CartService cartService;

    @Autowired
    BillService billService;

    @Autowired
    OrderProductService orderProductService;

    @GetMapping
    public String orderList(Principal principal,
                            ModelMap modelMap){

        User user = userService.getUserByEmail(principal.getName());
        List<Bill> bills = billService.getBillsByUserNo(user.getNo());

        modelMap.addAttribute("user", user);
        modelMap.addAttribute("bills", bills);
        return "order/list";
    }
//                OrderProduct : amount, status, bill, product
//                Bill : name, address, phone, regdate, user, List<OrderProduct>
    @PostMapping
    public String cartToOrder(Principal principal,
                              @RequestParam(name = "userName") String userName,
                              @RequestParam(name = "userAddress") String userAddress,
                              @RequestParam(name = "userPhone") String userPhone){
        User user = userService.getUserByEmail(principal.getName());
        List<Cart> carts = cartService.getCartsbyUserNo(user.getNo());
        List<OrderProduct> orderProducts = new ArrayList<>();
        Bill bill = new Bill();
        bill.setName(userName);
        bill.setRegdate(LocalDateTime.now());
        bill.setPhone(userPhone);
        bill.setOrderProducts(orderProducts);
        bill.setAddress(userAddress);
        bill.setUser(user);
        bill.setStatus("주문 확인");
        billService.addBill(bill);

        for(Cart cart : carts){
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setProduct(cart.getProduct());
            orderProduct.setAmount(cart.getAmount());
            orderProduct.setBill(bill);
            bill.addOrderProduct(orderProduct);
            orderProductService.addOrderProduct(orderProduct);
        }
        cartService.cleanCart();
        return "redirect:/order";
    }

    @GetMapping(path = "/carts")
    public String cartsOrder(Principal principal,
                           ModelMap modelMap){

        User user = userService.getUserByEmail(principal.getName());
        List<Cart> carts = cartService.getCartsbyUserNo(user.getNo());
        modelMap.addAttribute("carts", carts);

        Long totalPrice = cartService.getTotalPrice(user.getNo());
        modelMap.addAttribute("totalPrice", totalPrice);

        modelMap.addAttribute("user", user);

        return "/order/form";
    }

    @GetMapping(path = "/single")
    public String singleOrder(Principal principal,
                              @RequestParam(name = "productNo") Long productNo,
                              @RequestParam(name = "productAmount") int productAmount){

        cartService.addCart(principal.getName(),productNo,productAmount);

        return "redirect:/order/carts";
    }

    @DeleteMapping
    public String deleteOrder(){
        return "order/list";
    }

    @GetMapping(path = "/detail/{no}")
    public String orderProductList(@PathVariable Long no,
                                   ModelMap modelMap){
        Bill bill = billService.getBillByNo(no);

        List<OrderProduct> orderProducts = orderProductService.getOrderProductsbyBillNo(bill.getNo());
        modelMap.addAttribute("orderProducts", orderProducts);

        Long totalPrice = orderProductService.getTotalPrice(bill.getNo());
        modelMap.addAttribute("totalPrice", totalPrice);
        return "/order/detail";
    }

}
