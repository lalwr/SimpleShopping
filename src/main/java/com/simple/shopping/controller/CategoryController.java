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
    public void ajaxAddCategoryList(@RequestBody CategoryDto categoryDto
                            , HttpServletResponse response){

        categoryService.updateCategoryList(categoryDto.getCategoryList());

        try{
            PrintWriter pw = response.getWriter();

            pw.write("success");
        }catch(IOException ioe){
            ioe.printStackTrace();
        }

    }

    @DeleteMapping(path = "/list")
    public void ajaxDeleteCategoryList(@RequestBody CategoryDto categoryDto
                                    , HttpServletResponse response){

        try{
            PrintWriter pw = response.getWriter();

            categoryService.deleteCategoryList(categoryDto.getCategoryList());
            pw.write("success");
        }catch(IOException ioe){
            ioe.printStackTrace();
        }

    }

//    @DeleteMapping(path = "/list")
//    public void ajaxDeleteCategoryList(@RequestParam(name="param") String param,
//             HttpServletResponse response){
//        System.out.println("delete called");
//        System.out.println(param);
//
//    }
//    @PostMapping(path = "/list")
//    public void postDeleteCategoryList(@RequestParam(name="param") String param,
//                                       HttpServletResponse response){
//        System.out.println("post called");
//        System.out.println(param);
//
//    }

}
