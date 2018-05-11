package com.simple.shopping.controller;

import com.simple.shopping.domain.User;
import com.simple.shopping.domain.UserRole;
import com.simple.shopping.dto.UserJoinForm;
import com.simple.shopping.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(path = "/join")
    public String userJoin(UserJoinForm userJoinForm, ModelMap modelMap){
        User user = new User();
        modelMap.addAttribute("user", user);
        return "login/join";
    }

    @PostMapping(path = "/join")
    public String join(@Valid UserJoinForm userJoinForm, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "/login/join";
        }

        if(!userJoinForm.getPassword().equals(userJoinForm.getRePassword())){
            FieldError fieldError = new FieldError("userJoinForm", "rePassword", "암호가 일치하지 않습니다");
            bindingResult.addError(fieldError);
            return "login/join";
        }

        User userByEmail = userService.getUserByEmail(userJoinForm.getEmail());
        if(userByEmail != null){
            FieldError fieldError = new FieldError("userJoinForm", "email", "이미 존재하는 email입니다.");
            bindingResult.addError(fieldError);
            return "login/join";
        }

        User user = new User();
        BeanUtils.copyProperties(userJoinForm, user);

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

    @PostMapping(path = "/login")
    public String loginFail() { return "login/login"; }

    @GetMapping(path = "/user")
    public String user(Principal principal, ModelMap modelMap) {

        User user = userService.getUserByEmail(principal.getName());

        modelMap.addAttribute("user", user);

        return "users/user";
    }

    @PostMapping("/emailOverlap")
    @ResponseBody
    public String emailCheck(@RequestParam(value = "email", required = true) String email){

        String overlap = "false";

        if(userService.countByEmail(email) > 0){
            overlap = "overlap";
        }

        return overlap;

    }

    @PostMapping("/passwordCheck")
    @ResponseBody
    public String passwordCheck(Principal principal, @RequestParam(value = "email", required = true) String email, @RequestParam(value = "password", required = true) String password){

        String passwordCheck = "false";
        User user = userService.getUserByEmail(principal.getName());

        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        if(passwordEncoder.matches(password, user.getPassword())){
            passwordCheck = "true";
        }
        return passwordCheck;
    }

    @PutMapping("/update")
    public String userUpdate(Principal principal, User user){

        User userUpdate = userService.getUserByEmail(principal.getName());
        userUpdate.setName(user.getName());
        userUpdate.setAddress(user.getAddress());
        userUpdate.setPhone(user.getPhone());

        userService.addUser(userUpdate);

        return "redirect:/users/user";
    }

    @DeleteMapping("/delete")
    public String userDelete(Principal principal){

        User userDelete = userService.getUserByEmail(principal.getName());
        userDelete.setUse("N");

        userService.addUser(userDelete);

        return "redirect:/logout";
    }

}
