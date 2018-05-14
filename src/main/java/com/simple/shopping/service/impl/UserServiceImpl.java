package com.simple.shopping.service.impl;

import com.simple.shopping.domain.User;
import com.simple.shopping.domain.UserConnection;
import com.simple.shopping.repository.UserConnectionRepository;
import com.simple.shopping.repository.UserRepository;
import com.simple.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserConnectionRepository userConnectionRepository;

    @Override
    @Transactional(readOnly = true)
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = false)
    public User addUser(User user) {
        User saveuser = userRepository.save(user);
        return saveuser;
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByEmailAndUse(String email) {
        return userRepository.findUserByEmailAndUse(email);
    }

    @Override
    @Transactional(readOnly = true)
    public Long countByEmail(String email) {
        return userRepository.countByEmail(email);
    }

    @Override
    @Transactional(readOnly = false)
    public User getSocialUser(String type, String providerUserId){
        User user = userRepository.getSocialUser(type, providerUserId);
        return user;
    }

    @Override
    @Transactional
    public UserConnection addUserConnection(UserConnection userConnection) {
        return userConnectionRepository.save(userConnection);
    }

}
