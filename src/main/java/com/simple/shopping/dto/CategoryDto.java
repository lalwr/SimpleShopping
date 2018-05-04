package com.simple.shopping.dto;

import com.simple.shopping.domain.Category;

import java.util.List;

public class CategoryDto {

    List<Category> categoryList;

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
}
