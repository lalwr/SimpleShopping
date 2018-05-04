package com.simple.shopping.repository;

import com.simple.shopping.base.JpaQueryDslPredicateRepository;
import com.simple.shopping.domain.User;
import com.simple.shopping.domain.UserRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRoleRepository extends JpaQueryDslPredicateRepository<UserRole, Long>{

    public Long countUserRoleByRoleName(UserRole userRole);

}
