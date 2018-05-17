package com.simple.shopping.service;

import com.simple.shopping.domain.Category;

import java.util.List;

public interface CategoryService {
    Category addCategory(Category ctgr);
    List<Category> getCategoryList();
    Category updateCategory(Category ctgr);
    void deleteCategory(Category ctgr);
    void updateCategoryList(List<Category> categoryList);
    void deleteCategoryList(List<Category> categoryList);
    String checkDuplicateCategory(Category ctgr);
    String checkDuplicateCategoryList(List<Category> categoryList);
}
