package com.example.springboot_demo.dao;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.springboot_demo.model.User;

// 예제와 JUNIT버전이 다름(junit4 -> junit5)
@ExtendWith(MockitoExtension.class) // @RunWith -> @ExtendWith
@SpringBootTest
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("assertTrue 테스트")
    //UserList갯수가 0보다 큰지 체크
    void assertGetAllUsers() {
        List<User> res = userRepository.getAllUsers();
        //static method
        assertTrue(res.size() > 0, () -> "사용자 건수가 0보다 커야합니다.");
    }
    @Test
    @DisplayName("assertTrue 테스트")
    //UserList갯수가 0보다 큰지 체크
    void assertGetUserByUserid() {
        User user = userRepository.getUserByUserid(100);
        //static method
        assertNotNull(user);
    }
}
