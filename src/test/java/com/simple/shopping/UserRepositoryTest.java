package com.simple.shopping;

import com.simple.shopping.domain.User;
import com.simple.shopping.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    private User user;

    @Before
    public void init(){
        user = new User();
        user.setName("test");
        user.setEmail("test@gmail.com");
        user.setUse("Y");
        userRepository.save(user);
    }

    @Test
    public void notNull(){

        assertNotNull(userRepository);

    }

    @Test
    public void findAllTest() throws Exception{

        List<User> UserList = userRepository.findAll();
        assertFalse(UserList.isEmpty());

    }

    @Test
    public void findUserByEmailTest() throws Exception{

        User findUser = userRepository.findUserByEmail("test@gmail.com");
        assertEquals(user.getName(), findUser.getName());
        assertEquals(user.getEmail(), findUser.getEmail());

    }

    @Test
    public void findUserByEmailAndUseTest() throws Exception{

        User findUser = userRepository.findUserByEmailAndUse("test@gmail.com");
        assertEquals(user.getName(), findUser.getName());
        assertEquals(user.getEmail(), findUser.getEmail());
        assertEquals(user.getUse(), findUser.getUse());

    }

    @Test
    public void countByEmail() throws Exception{

        long count = userRepository.countByEmail("test@gmail.com");
        assertEquals(1L, count);

    }

}
