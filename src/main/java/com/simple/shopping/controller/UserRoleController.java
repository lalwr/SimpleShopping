package com.simple.shopping.controller;

import com.simple.shopping.domain.User;
import com.simple.shopping.domain.UserRole;
import com.simple.shopping.service.UserRoleService;
import com.simple.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/roles")
public class UserRoleController {

    @Autowired
    UserService userService;

    @Autowired
    UserRoleService userRoleService;

    @GetMapping
    public String role(ModelMap modelMap){

        List<User> userList = userService.getUsers();
        modelMap.addAttribute("userList" , userList);

        return "role/role";
    }

    @PostMapping
    public String roleSave(UserRole userRole){
        userRoleService.addRoles(userRole);

        return "redirect:/roles";
    }



}
