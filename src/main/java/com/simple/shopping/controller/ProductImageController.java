package com.simple.shopping.controller;

import com.simple.shopping.domain.ProductImage;
import com.simple.shopping.repository.ProductImageRepository;
import com.simple.shopping.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin/product/image")
public class ProductImageController {
    ProductImageService productImageService;

    @Autowired
    public ProductImageController(ProductImageService productImageService){
        this.productImageService = productImageService;
    }

    @RequestMapping("{imageNo}")
    public void downImageFile(@PathVariable(name = "imageNo") Long imageNo,
                              HttpServletRequest request,
                              HttpServletResponse response){
        ProductImage productImage = productImageService.findProductImageByNo(imageNo);
        productImageService.downProductImage(request, response, productImage);
    }
}
