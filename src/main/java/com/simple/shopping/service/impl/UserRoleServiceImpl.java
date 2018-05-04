package com.simple.shopping.service.impl;

import com.simple.shopping.domain.UserRole;
import com.simple.shopping.repository.UserRoleRepository;
import com.simple.shopping.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    UserRoleRepository userRoleRepository;

    @Override
    @Transactional(readOnly = false)
    public UserRole addRoles(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    @Override
    @Transactional
    public Long getUserRoleByRoleNameAndUserNo(UserRole userRole) {
        return userRoleRepository.countUserRoleByRoleName(userRole);
    }
}
