package com.simple.shopping.controller;

import com.simple.shopping.domain.Category;
import com.simple.shopping.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/admin/ctgr")
public class CategoryController {

    CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping
    public String categoryForm(){

        return "admin/category/category_form";
    }
    @PostMapping
    public String addCategory(@ModelAttribute Category category, ModelMap model){
        System.out.println(category.getName());
        categoryService.addCategory(category);
        return "redirect:admin/ctgr";
    }
}
