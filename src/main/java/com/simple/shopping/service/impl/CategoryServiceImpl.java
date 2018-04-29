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
    public List<Category> getCategories() {
        return categoryRepository.findAll();
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
        categoryRepository.delete(ctgr);
    }
}
