package com.simple.shopping.service;

import com.simple.shopping.domain.Category;

import java.util.List;

public interface CategoryService {
    Category addCategory(Category ctgr);
    List<Category> getCategories();
    Category updateCategory(Category ctgr);
    void deleteCategory(Category ctgr);
}
