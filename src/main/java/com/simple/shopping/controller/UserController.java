package com.simple.shopping.controller;

import com.simple.shopping.domain.User;
import com.simple.shopping.domain.UserRole;
import com.simple.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping(path = "/join")
    public String userJoin(ModelMap modelMap){
        User user = new User();
        modelMap.addAttribute("user", user);
        return "login/join";
    }

    @PostMapping(path = "/join")
    public String join(@ModelAttribute User user){

        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        UserRole userRole = new UserRole();
        userRole.setRoleName("USER");
        user.addUserRole(userRole);

        User saveUser = userService.addUser(user);

        return "redirect:/users/login";
    }

    @GetMapping(path = "/login")
    public String login() { return "login/login"; }

    @GetMapping(path = "/user")
    public String user() { return "users/user"; }

    @PostMapping
    @RequestMapping("/emailOverlap")
    @ResponseBody
    public String emailCheck(@RequestParam("email") String email){
        String overlap = "false";

        if(userService.countByEmail(email) > 0){
            overlap = "overlap";
        }

        return overlap;

    }

}
