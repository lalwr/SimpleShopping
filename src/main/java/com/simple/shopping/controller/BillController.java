package com.simple.shopping.controller;

import com.simple.shopping.domain.*;
import com.simple.shopping.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    @PostMapping
    public String cartToOrder(Principal principal,
                              HttpSession session,
                              @RequestParam(name = "userName") String userName,
                              @RequestParam(name = "userAddress") String userAddress,
                              @RequestParam(name = "userPhone") String userPhone,
                              @RequestParam(name = "userEmail", required = false) String userEmail){
        if(principal == null){
            session.setAttribute("email", userEmail);
            List<Cart> carts = (List<Cart>)session.getAttribute("carts");
            List<OrderProduct> orderProducts = new ArrayList<>();
            Bill bill = new Bill();
            bill.setName(userName);
            bill.setRegdate(LocalDateTime.now());
            bill.setPhone(userPhone);
            bill.setOrderProducts(orderProducts);
            bill.setAddress(userAddress);
            bill.setStatus("주문 확인");
            billService.addBill(bill);
            if(carts != null) {
                for (Cart cart : carts) {
                    int productStock = cart.getProduct().getAmount();
                    if (cart.getAmount() <= productStock) {
                        OrderProduct orderProduct = new OrderProduct();
                        orderProduct.setBill(bill);
                        orderProduct.setName(cart.getProduct().getName());
                        orderProduct.setImagePath(cart.getProduct().getImagePath());
                        orderProduct.setAmount(cart.getAmount());
                        orderProduct.setPrice(cart.getProduct().getPrice());
                        bill.addOrderProduct(orderProduct);
                        orderProductService.addOrderProduct(orderProduct);
                        cart.getProduct().setAmount(cart.getProduct().getAmount() - cart.getAmount());
                    } else {
                        return "redirect:cart/list";
                    }
                }
                carts.clear();
                return "redirect:/order/formUnsigned";
            }
            return "redirect:/product/list";
        }else {
            User user = userService.getUserByEmail(principal.getName());
            Long totalPrice = cartService.getTotalPrice(user.getNo());
            if (totalPrice != 0) {
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

                for (Cart cart : carts) {
                    int productStock = cart.getProduct().getAmount();
                    if (cart.getAmount() <= productStock) {
                        OrderProduct orderProduct = new OrderProduct();
                        orderProduct.setProduct(cart.getProduct());
                        orderProduct.setImagePath(cart.getProduct().getImagePath());
                        orderProduct.setName(cart.getProduct().getName());
                        orderProduct.setAmount(cart.getAmount());
                        orderProduct.setPrice(cart.getProduct().getPrice());
                        orderProduct.setBill(bill);
                        bill.addOrderProduct(orderProduct);
                        orderProductService.addOrderProduct(orderProduct);
                        cart.getProduct().setAmount(cart.getProduct().getAmount() - cart.getAmount());
                    } else {
                        return "redirect:cart/list";
                    }
                }
                cartService.cleanCart(user.getNo());

                return "redirect:/order";
            } else {
                return "redirect:/product/list";
            }
        }
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

    @GetMapping(path = "/unsigned")
    public String orderListUnsigned(@RequestParam(name = "userPhone") String phone,
                                    ModelMap modelMap){
        List<Bill> bills = billService.getBillsByPhone(phone);
        modelMap.addAttribute("bills", bills);

        return "order/listUnsigned";
    }

    @GetMapping(path = "/formUnsigned")
    public String orderListFormUnsigned(){
        return "order/formUnsigned";
    }

    @GetMapping(path = "/carts")
    public String cartsOrder(Principal principal,
                             HttpSession session,
                             ModelMap modelMap){
        if(principal == null){
            List<Cart> carts = (List<Cart>) session.getAttribute("carts");
            if(carts != null) {
                for (Cart cart : carts) {
                    int productStock = cart.getProduct().getAmount();
                    if (productStock == 0) {
                        carts.remove(cart);
                    }
                }
            }
            modelMap.addAttribute("carts", carts);

            int totalPrice = 0;
            if(carts != null) {
                for (Cart cart : carts) {
                    totalPrice += cart.getProduct().getPrice() * cart.getAmount();
                }
            }
            modelMap.addAttribute("totalPrice", totalPrice);

            User user = new User();
            modelMap.addAttribute("user", user);

            return "/order/form";
        }else {
            User user = userService.getUserByEmail(principal.getName());
            modelMap.addAttribute("user", user);

            List<Cart> carts = cartService.getCartsbyUserNo(user.getNo());
            for (Cart cart : carts) {
                int productStock = cart.getProduct().getAmount();
                if (productStock == 0) {
                    cartService.deleteCart(cart.getUser().getEmail(), cart.getProduct().getNo());
                }
            }
            modelMap.addAttribute("carts", carts);

            Long totalPrice = cartService.getTotalPrice(user.getNo());
            modelMap.addAttribute("totalPrice", totalPrice);


            return "/order/form";
        }
    }

    @GetMapping(path = "/single")
    public String singleOrder(Principal principal,
                              HttpServletRequest request,
                              @RequestParam(name = "productNo") Long productNo,
                              @RequestParam(name = "productAmount") int productAmount){
        if(principal == null){
            HttpSession session = request.getSession();
            List<Cart> carts = (List<Cart>)session.getAttribute("carts");
            if(carts.size() == 0){
                Cart cart = new Cart();
                cart.setAmount(productAmount);
                cart.setProduct(productService.getProductByNo(productNo));
                carts.add(cart);
                session.setAttribute("carts", carts);
            }else{
                if (carts != null) {
                    for (Cart cart : carts) {
                        if (cart.getProduct().getNo().equals(productNo)){
                            cart.setAmount(cart.getAmount() + productAmount);
                            return "redirect:/order/carts";
                        }
                    }
                    Cart cart = new Cart();
                    cart.setAmount(productAmount);
                    cart.setProduct(productService.getProductByNo(productNo));
                    carts.add(cart);
                }
            }
            return "redirect:/order/carts";
        }else {
            int productStock = productService.getProductByNo(productNo).getAmount();
            if (productAmount <= productStock) {
                cartService.addCart(principal.getName(), productNo, productAmount);
            }
            return "redirect:/order/carts";
        }
    }

}
