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
import java.security.Principal;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping(path = "/join")
    public String userJoin(){
        return "login/join";
    }

    @PostMapping(path = "/join")
    public String join(@ModelAttribute User user){

        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setUse("Y");

        UserRole userRole = new UserRole();
        userRole.setRoleName("USER");
        user.addUserRole(userRole);

        User saveUser = userService.addUser(user);

        return "redirect:/users/login";
    }

    @GetMapping(path = "/login")
    public String login() { return "login/login"; }

    @GetMapping(path = "/user")
    public String user(Principal principal, ModelMap modelMap) {

        User user = userService.getUserByEmail(principal.getName());

        modelMap.addAttribute("user", user);

        return "users/user";
    }

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

    @PostMapping
    @RequestMapping("/passwordCheck")
    @ResponseBody
    public String passwordCheck(Principal principal, @RequestParam("email") String email, @RequestParam("password") String password){

        String passwordCheck = "false";
        User user = userService.getUserByEmail(principal.getName());

        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        if(passwordEncoder.matches(password, user.getPassword())){
            passwordCheck = "true";
        }
        return passwordCheck;
    }

    @PutMapping
    @RequestMapping("/update")
    public String userUpdate(Principal principal, User user){

        User userUpdate = userService.getUserByEmail(principal.getName());
        userUpdate.setName(user.getName());
        userUpdate.setAddress(user.getAddress());
        userUpdate.setPhone(user.getPhone());

        userService.addUser(userUpdate);

        return "redirect:/users/user";
    }

    @DeleteMapping
    @RequestMapping("/delete")
    public String userDelete(Principal principal){

        User userDelete = userService.getUserByEmail(principal.getName());
        userDelete.setUse("N");

        userService.addUser(userDelete);

        return "redirect:/logout";
    }

}
