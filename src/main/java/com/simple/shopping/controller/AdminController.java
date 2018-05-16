package com.simple.shopping.controller;

import com.simple.shopping.domain.Category;
import com.simple.shopping.domain.Product;
import com.simple.shopping.domain.ProductImage;
import com.simple.shopping.dto.CategoryDto;
import com.simple.shopping.dto.Pagination;
import com.simple.shopping.dto.ProductDto;
import com.simple.shopping.service.AdminService;
import com.simple.shopping.service.CategoryService;
import com.simple.shopping.service.ProductImageService;
import com.simple.shopping.service.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    AdminService adminService;
    CategoryService categoryService;
    ProductImageService productImageService;

    @Autowired
    public AdminController(AdminService adminService, CategoryService categoryService, ProductImageService productImageService){
        this.adminService = adminService;
        this.categoryService = categoryService;
        this.productImageService = productImageService;
    }

    @GetMapping(path = "/product/list")
    public String productList(@RequestParam(name="page", defaultValue="1") int page
                            ,@RequestParam(name="searchType", required = false) String searchType
                            ,@RequestParam(name="searchStr", required = false) String searchStr
                            ,@RequestParam(name="categoryNo", required = false) Long categoryNo
                            ,ModelMap modelMap){
        System.out.println("categoryNo: "+categoryNo);
//        categoryNo = 1L;
        Pagination pagination = new Pagination(page, searchType, searchStr);
        System.out.println("pagination: "+pagination);
        Page<Product> productList = adminService.getProductList(pagination, categoryNo);




        pagination.setButtonCount(5);
        pagination.setTotalCount(productList.getTotalElements());
        pagination.setTotalPage(productList.getTotalPages());

        modelMap.addAttribute("pagination", pagination);
        modelMap.addAttribute("productList", productList);
        modelMap.addAttribute("categoryList", categoryService.getCategoryList());
        modelMap.addAttribute("categoryNo", categoryNo);

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

        if(product.getProductImage()!=null){
            System.out.println("=========================================");
            System.out.println(product.getProductImage().getSaveName());
        }

        model.addAttribute("categoryList", categoryList);
        model.addAttribute("write",write);

        return "/admin/product/product_detail";
    }

    @PostMapping(path = "/product")
    public String addProduct(@RequestParam(name = "file", required = false) MultipartFile multipartFile,
                             @ModelAttribute Product product,
                             HttpSession session
                            ,HttpServletRequest request){
        ProductImage productImage = productImageService.saveProductImage(multipartFile);
        product.setProductImage(productImage);
        adminService.addProduct(product);
        String realPath = session.getServletContext().getRealPath("/");
        System.out.println("============================");
        System.out.println("realPath " +realPath);
        return "redirect:/admin/product/list";
    }

    @PutMapping(path = "/product")
    public String updateProduct(@RequestBody ProductDto productDto){
        System.out.println("categoryDto.getCategoryList().size(): "+productDto.getProductList().size());
//        adminService.addProduct(product);
        return "redirect:/admin/product/list";
    }

    @DeleteMapping(path="/product/list")
    @ResponseBody
    public String ajaxDeleteProduct(@RequestBody ProductDto productDto){
        String message = "success";
        for (Product product: productDto.getProductList()){
            System.out.println(product.getNo());
        }
        adminService.deleteProductList(productDto.getProductList());

        return message;
    }


}
