package com.simple.shopping.service.impl;

import com.simple.shopping.domain.Category;
import com.simple.shopping.repository.CategoryRepository;
import com.simple.shopping.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public Category addCategory(Category ctgr) {
        Category savedCtgr = categoryRepository.save(ctgr);
        return savedCtgr;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> getCategoryList() {
        String use = "Y";
        return categoryRepository.findAllByUse(use);
    }

    @Override
    @Transactional
    public Category updateCategory(Category ctgr) {
        Category categoryToUpdate = categoryRepository.getOne(ctgr.getNo());
        categoryToUpdate.setName(ctgr.getName());
        return categoryRepository.save(categoryToUpdate);
    }

    @Override
    @Transactional
    public void deleteCategory(Category ctgr) {
        Category categoryToUpdate = categoryRepository.getOne(ctgr.getNo());
        categoryToUpdate.setUse("N");
        categoryRepository.save(categoryToUpdate);
    }

    @Override
    @Transactional
    public void updateCategoryList(List<Category> categoryList) {
        for(Category category: categoryList){
            updateCategory(category);
        }
    }

    @Override
    @Transactional
    public void deleteCategoryList(List<Category> categoryList) {
        for(Category category: categoryList){
            deleteCategory(category);
        }
    }
}
