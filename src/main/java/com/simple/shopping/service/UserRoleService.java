package com.simple.shopping.service;

import com.simple.shopping.domain.UserRole;

public interface UserRoleService {

    UserRole addRoles(UserRole userRole);
    Long getUserRoleByRoleNameAndUserNo(UserRole userRole);

}
