package com.simple.shopping.controller;

import com.simple.shopping.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    @GetMapping(path = "/join")
    public String userJoin(ModelMap modelMap){
        User user = new User();
        modelMap.addAttribute("user", user);
        return "users/join";
    }


}
