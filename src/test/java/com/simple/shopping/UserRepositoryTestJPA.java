package com.simple.shopping;

import com.simple.shopping.domain.User;
import com.simple.shopping.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTestJPA {

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
