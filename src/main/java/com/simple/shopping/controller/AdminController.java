package com.simple.shopping.controller;

import com.simple.shopping.domain.Category;
import com.simple.shopping.domain.Product;
import com.simple.shopping.dto.CategoryDto;
import com.simple.shopping.dto.Pagination;
import com.simple.shopping.service.AdminService;
import com.simple.shopping.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    AdminService adminService;
    CategoryService categoryService;

    @Autowired
    public AdminController(AdminService adminService, CategoryService categoryService){
        this.adminService = adminService;
        this.categoryService = categoryService;
    }

    @GetMapping(path = "/product/list")
    public String productList(@RequestParam(name="page", defaultValue="1") int page
                            ,@RequestParam(name="SearchType", required = false) String searchType
                            ,@RequestParam(name="SearchStr", required = false) String searchStr
                            ,ModelMap modelMap){
        Pagination pagination = new Pagination(page, searchType, searchStr);

        Page<Product> productList = adminService.getProductList(pagination, searchType, searchStr);


        pagination.setButtonCount(5);
        pagination.setTotalCount(productList.getTotalElements());
        pagination.setTotalPage(productList.getTotalPages());

        modelMap.addAttribute("pagination", pagination);
        modelMap.addAttribute("productList", productList);

        System.out.println(pagination.getStartPage());
        System.out.println(pagination.getEndPage());
        System.out.println(pagination.getCurPage());
        return "/admin/product/product_list";
    }

    @GetMapping(path = "/product")
    public String product(@ModelAttribute Product product,
                          @RequestParam(name="write", required = false) Integer write,
                          @RequestParam(name="no", required = false) Long no,
                          ModelMap model){

        if(no != null){
            Product savedProduct = adminService.findProduct(no);
            model.addAttribute("product", savedProduct);
        }
        List<Category> categoryList = categoryService.getCategoryList();

        model.addAttribute("categoryList", categoryList);
        model.addAttribute("write",write);

        return "/admin/product/product_detail";
    }

    @PostMapping(path = "/product")
    public String addProduct(@ModelAttribute Product product){
        adminService.addProduct(product);
        return "redirect:/admin/product/list";
    }

    @PutMapping(path = "/product")
    public String updateProduct(@RequestBody CategoryDto categoryDto){
        System.out.println("categoryDto.getCategoryList().size(): "+categoryDto.getCategoryList().size());
//        adminService.addProduct(product);
        return "redirect:/admin/product/list";
    }


}
