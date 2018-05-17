package com.simple.shopping.repository;

import com.simple.shopping.base.JpaQueryDslPredicateRepository;
import com.simple.shopping.domain.Category;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryRepository extends JpaQueryDslPredicateRepository<Category, Long> {
    List<Category> findAllByUse(String use);
    int countCategoryByName(String name);
}
