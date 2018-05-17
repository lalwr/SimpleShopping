package com.simple.shopping;

import com.simple.shopping.domain.User;
import com.simple.shopping.domain.UserRole;
import com.simple.shopping.repository.UserRepository;
import com.simple.shopping.repository.UserRoleRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserRoleRepositoryTest {

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    UserRepository userRepository;

    private UserRole userRole;
    private User user;

    @Before
    public void init(){
        user = new User();
        user.setName("test");
        user.setEmail("test@gmail.com");
        user.setUse("Y");
        userRepository.save(user);
        userRole = new UserRole();
        userRole.setRoleName("testRole");
        userRole.setUser(user);
        userRoleRepository.save(userRole);
    }

    @Test
    public void countUserRoleByRoleNameAndUserNoTest(){

        User findUser = userRepository.findUserByEmail("test@gmail.com");
        long count = userRoleRepository.countUserRoleByRoleNameAndUserNo(findUser.getNo(), "testRole");

        assertEquals(1L, count);
    }

    @Test
    public void deleteRoles(){
        User findUser = userRepository.findUserByEmail("test@gmail.com");
        long findUserNo =  findUser.getRoles().get(0).getNo(); //유저 번호
        Optional<UserRole> findUserRole = userRoleRepository.findById(findUserNo);
        long findUserRoleNo = findUserRole.get().getNo(); //유저의 권한 번호
        userRoleRepository.deleteById(findUserRoleNo);

        long count = userRoleRepository.countUserRoleByRoleNameAndUserNo(findUserNo, "testRole");

        assertEquals(0L, count);
    }

    @Test
    public void getUserRoleByNo(){
        User findUser = userRepository.findUserByEmail("test@gmail.com");
        long findUserNo =  findUser.getRoles().get(0).getNo(); //유저 번호
        Optional<UserRole> findUserRole = userRoleRepository.findById(findUserNo);
        long findUserRoleNo = findUserRole.get().getNo(); //유저의 권한 번호

        long count = userRoleRepository.countUserRoleByNo(findUserRoleNo);

        assertEquals(1L, count);
    }
}
