package com.simple.shopping.controller;

import com.simple.shopping.domain.User;
import com.simple.shopping.domain.UserRole;
import com.simple.shopping.service.UserRoleService;
import com.simple.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @PostMapping
    @ResponseBody
    public String roleSave(@RequestParam(value = "userNo", required = true) Long userNo, @RequestParam(value ="roleName", required = true) String roleName){

        String result = "overlap";

        Long cnt = userRoleService.getUserRoleByRoleNameAndUserNo(userNo, roleName);
        if( cnt == 0){
            UserRole userRole = new UserRole();
            User user = new User();
            user.setNo(userNo);
            userRole.setUser(user);
            userRole.setRoleName(roleName);
            userRoleService.addRoles(userRole);
            result = "true";
        }

        return result;
    }

    @DeleteMapping("/{roleNo}")
    @ResponseBody
    public String roleDelete(@PathVariable("roleNo") Long roleNo){
        String result = "noRole";

        Long cnt = userRoleService.getUserRoleByNo(roleNo);
        if( cnt != 0){
            userRoleService.deleteRoles(roleNo);
            result = "true";
        }

        return result;
    }


}
