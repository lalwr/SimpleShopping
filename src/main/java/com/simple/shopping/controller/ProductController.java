package com.simple.shopping.controller;

import com.simple.shopping.PageManager;
import com.simple.shopping.domain.Product;
import com.simple.shopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping(path = "/list")
    public String productList(@RequestParam(name = "category", required = false, defaultValue = "All") String category,
                              @RequestParam(name = "search", required = false, defaultValue = "") String search,
                              @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                              ModelMap modelMap){
        PageManager pageManager = new PageManager();
        Page<Product> products;
        products = productService.getProducts(search,category, page);
        if("All".equals(category) && "".equals(search)){
            pageManager.setTotalPage(PageManager.maxProduct, productService.countAll());
        }else if ("All".equals(category) && !"".equals(search)){
            pageManager.setTotalPage(PageManager.maxProduct, productService.countAllByName(search));
        }else{
            pageManager.setTotalPage(PageManager.maxProduct, productService.countAllByCategoryAndName(search, category));
        }
        modelMap.addAttribute("products", products);
        pageManager.setCurrentPage(page);
        pageManager.setPageOffset(page);
        pageManager.setCategory(category);
        pageManager.setSearch(search);
        modelMap.addAttribute("pageManager", pageManager);

        return "product/list";
    }

    @GetMapping(path = "/detail/{no}")
    public String productDetail(@PathVariable Long no,
                                ModelMap modelMap){
        Product product = productService.getProductByNo(no);
        modelMap.addAttribute("product", product);
        return "product/detail";
    }
}
