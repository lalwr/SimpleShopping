package com.simple.shopping.controller;

import com.simple.shopping.domain.User;
import com.simple.shopping.domain.UserRole;
import com.simple.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(path = "/join")
    public String userJoin(ModelMap modelMap){
        User user = new User();
        modelMap.addAttribute("user", user);
        return "users/join";
    }

    @GetMapping(path = "/login")
    public String login() { return "users/login"; }

    @PostMapping(path = "/join")
    public String join(@ModelAttribute User user){

        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        List<UserRole> userRoleList = new ArrayList<>();
        UserRole userRole = new UserRole();
        userRole.setRoleName("USER");
        user.setRoles(userRoleList);

        User saveUser = userService.addUser(user);

        return "redirect:/";
    }

    @GetMapping(path = "/user")
    public String user() { return "users/user"; }

}
