package com.simple.shopping.repository;

import com.simple.shopping.base.JpaQueryDslPredicateRepository;
import com.simple.shopping.domain.User;

//@Respository를 붙이지 않아도 Respository가 자동으로 등록 된다
public interface UserRepository extends JpaQueryDslPredicateRepository<User, Long> {

    public User findUserByEmail(String email);
}
// spring data method query
