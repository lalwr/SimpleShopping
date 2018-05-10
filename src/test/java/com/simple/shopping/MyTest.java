package com.simple.shopping;

import com.simple.shopping.repository.AdminRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MyTest {
    @Autowired
    private AdminRepository adminRepository;

    @Test
    public void queryTest(){
        assertThat(adminRepository).isNotNull();
    }
}
