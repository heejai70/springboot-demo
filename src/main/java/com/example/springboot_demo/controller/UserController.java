package com.example.springboot_demo.controller;

import com.example.springboot_demo.annotation.TokenRequired;
import com.example.springboot_demo.model.User;
import com.example.springboot_demo.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger logger =
            LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @TokenRequired
    @GetMapping("")
    public List<User> getAllUsers() {
        
//        int a = 3/0; //divide 0 Exception 발생 테스트
        return userService.getUsers();
    }

    @GetMapping("/{userid}")
    public User getUserByUserid(@PathVariable Integer userid) {
        logger.debug("" + userid);
        int a = 3 / 0;
        return userService.getUserById(userid);
    }

    @PostMapping("")
    public User registUser(@RequestBody User user) {
        System.out.println("called UserController.registUser() : " + user);
        return userService.registUser(user);
    }

    @PutMapping("/{userid}")
    public void modifyUser(
            @PathVariable Integer userid,
            @RequestBody User user) {
        userService.modifyUser(userid, user);
    }

    @DeleteMapping("/{userid}")
    public void removeUser(@PathVariable Integer userid) {
        userService.removeUser(userid);
    }
}