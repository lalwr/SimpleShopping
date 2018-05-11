package com.simple.shopping.service;

import com.simple.shopping.domain.User;
import com.simple.shopping.domain.UserConnection;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    User addUser(User user);
    User getUserByEmail(String email);
    User getUserByEmailAndUse(String email);
    Long countByEmail(String email);

    public User getSocialUser(String type, String providerUserId);
    public UserConnection addUserConnection(UserConnection userConnection);
}
