package com.simple.shopping.controller;

import com.simple.shopping.domain.Product;
import com.simple.shopping.dto.Pagination;
import com.simple.shopping.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
//@SessionAttributes({"product"})
@RequestMapping("/admin")
public class AdminController {
    AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    @GetMapping(path = "/product/list")
    public String productList(@RequestParam(name="page", defaultValue="1") int page
                            ,@RequestParam(name="SearchType", required = false) String searchType
                            ,@RequestParam(name="SearchStr", required = false) String searchStr
                            ,ModelMap modelMap){

        Page<Product> productList = adminService.getProductList(page, searchType, searchStr);

        Pagination pagination = new Pagination();
        pagination.setButtonCount(5);
        pagination.setCurPage(page);
        pagination.setSearchType(searchType);
        pagination.setSearchStr(searchStr);
        pagination.setTotalCount(productList.getTotalElements());
        pagination.setTotalPage(productList.getTotalPages());

        modelMap.addAttribute("pagination", pagination);
        modelMap.addAttribute("productList", productList);

        System.out.println(productList.getSize());
        return "/admin/product/product_list";
    }

    @GetMapping(path = "/product")
    public String product(@ModelAttribute Product product, ModelMap model){
//        model.addAttribute("write", "true");
        return "/admin/product/product_writeform";
    }

    @PostMapping(path = "/product")
    public String addProduct(@ModelAttribute Product product){
        adminService.addProduct(product);
        return "redirect:/admin/product/list";
    }


}
