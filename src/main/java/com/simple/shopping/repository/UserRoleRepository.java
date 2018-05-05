package com.simple.shopping.repository;

import com.simple.shopping.base.JpaQueryDslPredicateRepository;
import com.simple.shopping.domain.UserRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRoleRepository extends JpaQueryDslPredicateRepository<UserRole, Long>{

    @Query("SELECT COUNT(r) FROM UserRole r WHERE r.user.no = :userNo AND r.roleName = :roleName")
    public Long countUserRoleByRoleNameAndUserNo(@Param("userNo") Long userNo, @Param("roleName") String roleName);
    public Long countUserRoleByNo(Long roleNo);


}
