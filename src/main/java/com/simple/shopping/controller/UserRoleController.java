package com.simple.shopping.controller;

import com.simple.shopping.domain.User;
import com.simple.shopping.domain.UserRole;
import com.simple.shopping.service.UserRoleService;
import com.simple.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/roles")
public class UserRoleController {

    @Autowired
    UserService userService;

    @Autowired
    UserRoleService userRoleService;

    @GetMapping
    public String role(ModelMap modelMap){

        List<User> userList = userService.getUsers();
        modelMap.addAttribute("userList" , userList);

        return "role/list";
    }


    @GetMapping("role")
    public String roleForm(){
        return "role/role";
    }

    @PostMapping
    @ResponseBody
    public String roleSave(UserRole userRole){

        String result = "overlap";

        if(userRoleService.getUserRoleByRoleNameAndUserNo(userRole) == 0){
            userRoleService.addRoles(userRole);
            result = "true";
        }

        return result;
    }

}
