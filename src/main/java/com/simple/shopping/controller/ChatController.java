package com.simple.shopping.controller;

import com.simple.shopping.service.ChatService;
import com.simple.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chatrooms")
public class ChatController {

    /*@Autowired
    ChatService chatService;*/

    @Autowired
    UserService userService;
}
