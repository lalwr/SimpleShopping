package com.simple.shopping.controller;

import com.simple.shopping.domain.Category;
import com.simple.shopping.dto.CategoryDto;
import com.simple.shopping.service.CategoryService;
import com.simple.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/admin/ctgr")
public class CategoryController {

    CategoryService categoryService;
    UserService userService;

    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping
    public String categoryForm(){

        return "admin/category/category_form";
    }
    @PostMapping
    public String addCategory(@ModelAttribute Category category){
        categoryService.addCategory(category);
        return "redirect:/admin/ctgr/list";
    }

    @PutMapping(path = "/list")
    public String updateCategories(@ModelAttribute CategoryDto categoryDto){
        for(CategoryDto category : categoryDto.getCtgrs()){
            if(category.getNo() != null){
                Category ctgr = new Category();
                ctgr.setNo(category.getNo());
                ctgr.setName(category.getName());
                categoryService.updateCategory(ctgr);
            }
            System.out.println(category.getNo()+" : "+category.getName());
        }
//        categoryService.addCategory(category1);
        return "redirect:/admin/ctgr/list";
    }

    @DeleteMapping(path = "/list")
    public String deleteCategories(@ModelAttribute CategoryDto categoryDto){
        for(CategoryDto category : categoryDto.getCtgrs()){
            if(category.getNo() != null){
                Category ctgr = new Category();
                ctgr.setNo(category.getNo());
                categoryService.deleteCategory(ctgr);
            }
        }
        return "redirect:/admin/ctgr/list";
    }

    @GetMapping(path = "/list")
    public String categoryList(ModelMap model){
        List<Category> categories = categoryService.getCategories();

        model.addAttribute("ctgrs", categories);
        return "admin/category/category_list";
    }
    @PostMapping(path = "/list")
    public String postCategoryList(@RequestParam(name= "ctgrBox", required = false) List<String> checkList){
        System.out.println("post called");
        for(String check: checkList){
            System.out.println(check);
        }
        return "admin/category/category_list";
    }
}
