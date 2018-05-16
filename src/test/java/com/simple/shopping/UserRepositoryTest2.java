package com.simple.shopping;

import com.simple.shopping.domain.User;
import com.simple.shopping.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void notNull(){
        assertNotNull(userRepository);
    }

    @Test
    public void findAllTest(){

        List<User> UserList = userRepository.findAll();
        assertFalse(UserList.isEmpty());

    }
}
