package com.simple.shopping.controller;

import com.simple.shopping.PageManager;
import com.simple.shopping.domain.Product;
import com.simple.shopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping(path = "/list")
    public String productList(@RequestParam(name = "category", required = false) String category,
                              @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                              ModelMap modelMap){
        //데이터베이스에서 상품 리스트 가져오기
        // 한번에 화면에 출력되는 상품 수, 현재 페이지, 총 페이지 수, 앞으로 가기, 뒤로가기, 맨 앞으로 가기, 맨 뒤로 가기
        if(("".equals(category) || category == null)) {
            Page<Product> products = productService.getProducts(page);
            modelMap.addAttribute("products", products);
        }else {
            Page<Product> products = productService.getProducts(category, page);
            modelMap.addAttribute("products", products);
        }
        return "product/list";
    }

    @GetMapping(path = "/detail/{no}")
    public String productDetail(@PathVariable Long no,
                                ModelMap modelMap){
        //데이터베이스에서 상품 번호에 해당하는 상품 정보 가져오기
        Product product = productService.getProductByNo(no);
        modelMap.addAttribute("product", product);
        return "product/detail";
    }
}
