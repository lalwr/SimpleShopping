package com.simple.shopping;

import com.simple.shopping.domain.User;
import com.simple.shopping.repository.UserRepository;
import com.simple.shopping.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired
    UserService userService;

    @MockBean
    UserRepository userRepository;

    private User user;

    @Test
    public void notNull(){

        assertNotNull(userService);

    }

    @Before
    public void init(){

        user = new User();
        user.setName("test");
        user.setEmail("test@gmail.com");
        user.setUse("Y");
        userRepository.save(user);

    }

    @Test
    public void getUsers(){

        List<User> userList = new ArrayList();
        userList.add(user);
        given(userService.getUsers()).willReturn(userList);

        List<User> list = userService.getUsers();

        for(User user : list){
            System.out.println("===============================================");
            System.out.println(user.getName());
            System.out.println(user.getEmail());
            System.out.println("===============================================");
        }

    }

    @Test
    public void getUserByEmailTest(){

        given(userService.getUserByEmail("admin@gmail.com")).willReturn(user);

        User getUser = userService.getUserByEmail("admin@gmail.com");

        assertEquals(user.getNo() ,getUser.getNo());
    }
}
