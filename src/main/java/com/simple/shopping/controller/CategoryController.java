package com.simple.shopping.controller;

import com.simple.shopping.domain.Category;
import com.simple.shopping.dto.CategoryDto;
import com.simple.shopping.service.CategoryService;
import com.simple.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/admin/category")
public class CategoryController {

    CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping
    public String categoryForm(){
        return "admin/category/category_form";
    }
    
    @GetMapping(path = "/list")
    public String categoryList(ModelMap model){
        List<Category> categories = categoryService.getCategoryList();

        model.addAttribute("categoryList", categories);

        return "admin/category/category_list";
    }

    @PostMapping
    public String addCategory(@ModelAttribute Category category){
        categoryService.addCategory(category);

        return "redirect:/admin/category/list";
    }

    @PutMapping(path = "/list")
    public String updateCategoryList(@ModelAttribute CategoryDto categoryDto){
        List<Category> categoryList = new ArrayList<Category>();

        for(Category category : categoryDto.getCategoryList()){
            if(category.getNo() != null){
                categoryList.add(category);
            }
        }

        categoryService.updateCategoryList(categoryList);

        return "redirect:/admin/category/list";
    }

    @DeleteMapping(path = "/list")
    public String deleteCategoryList(@ModelAttribute CategoryDto categoryDto){
        List<Category> categoryList = new ArrayList<Category>();

        for(Category category : categoryDto.getCategoryList()){
            if(category.getNo() != null){
                categoryList.add(category);
            }
        }

        categoryService.deleteCategoryList(categoryList);

        return "redirect:/admin/category/list";
    }



}
