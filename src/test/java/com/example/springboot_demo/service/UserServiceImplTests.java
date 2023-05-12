package com.example.springboot_demo.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.springboot_demo.model.User;

@ExtendWith(MockitoExtension.class) // @RunWith -> @ExtendWith
@SpringBootTest
public class UserServiceImplTests {
    @Autowired
    private UserServiceImpl userService;

    @Test
    public void assertRegistUser() {
        User user = new User(1000, "kkkim");
        User res = userService.registUser(user);
        assertNotNull(res);
    }
}
