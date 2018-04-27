package com.simple.shopping.service.impl;

import com.simple.shopping.domain.Category;
import com.simple.shopping.repository.CategoryRepository;
import com.simple.shopping.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryServiceImpl implements CategoryService{

    CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public Category addCategory(Category ctgr) {
        Category savedCtgr = categoryRepository.save(ctgr);
        return savedCtgr;
    }
}
