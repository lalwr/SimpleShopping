package com.simple.shopping.controller;

import com.simple.shopping.domain.Category;
import com.simple.shopping.dto.CategoryDto;
import com.simple.shopping.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

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
    public String categoryList(ModelMap model, HttpServletRequest request, RedirectAttributes redirectAttributes){
        List<Category> categories = categoryService.getCategoryList();
        Map flashMap = RequestContextUtils.getInputFlashMap(request);

        if(flashMap!=null){
            model.addAttribute("message", flashMap.get("message"));
        }
        model.addAttribute("categoryList", categories);

        return "admin/category/category_list";
    }

    @PostMapping
    public String addCategory(@ModelAttribute Category category, RedirectAttributes redirectAttributes){
        String message = categoryService.checkDuplicateCategory(category);
        if("".equals(message)){
            categoryService.addCategory(category);
        }

        message = ( "".equals(message) ) ? "" : message+" 는 중복된 카테고리입니다.";
        redirectAttributes.addFlashAttribute("message", message);

        return "redirect:/admin/category/list";
    }

    @PutMapping(path="/list")
    @ResponseBody
    public String ajaxUpdateCategoryList(@RequestBody CategoryDto categoryDto
                            ){
        String message = categoryService.checkDuplicateCategoryList(categoryDto.getCategoryList());
        //중복 값이 없는경우
        if("".equals(message)){
            categoryService.updateCategoryList(categoryDto.getCategoryList());
            message = "success";
        }



        return message;

    }

    @DeleteMapping(path = "/list")
    @ResponseBody
    public String ajaxDeleteCategoryList(@RequestBody CategoryDto categoryDto
                                    ){

            categoryService.deleteCategoryList(categoryDto.getCategoryList());

        return"success";

    }


}
