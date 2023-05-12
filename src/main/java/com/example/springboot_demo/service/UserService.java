package com.example.springboot_demo.service;

import java.util.List;
import com.example.springboot_demo.model.User;

public interface UserService {
    List<User> getUsers();

    User getUserById(Integer userid);

    User registUser(User user);

    void modifyUser(Integer userid, User user);

    void removeUser(Integer userid);
}
