package com.simple.shopping.controller;

import com.simple.shopping.domain.Category;
import com.simple.shopping.dto.CategoryDto;
import com.simple.shopping.service.CategoryService;
import com.simple.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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

    @PutMapping(path="/list")
    @ResponseBody
    public String ajaxAddCategoryList(@RequestBody CategoryDto categoryDto
                            , HttpServletResponse response){

        categoryService.updateCategoryList(categoryDto.getCategoryList());

        return "success";

    }

    @DeleteMapping(path = "/list")
    @ResponseBody
    public String ajaxDeleteCategoryList(@RequestBody CategoryDto categoryDto
                                    , HttpServletResponse response){

            categoryService.deleteCategoryList(categoryDto.getCategoryList());

        return"success";

    }


}
