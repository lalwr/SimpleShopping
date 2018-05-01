package com.simple.shopping.service;

import com.simple.shopping.domain.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    User addUser(User user);
    User getUserByEmail(String email);
    Long countByEmail(String email);
    String countByEmailAndPassword(String email, String password);
}
