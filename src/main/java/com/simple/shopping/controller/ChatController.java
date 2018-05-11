package com.simple.shopping.controller;

import com.simple.shopping.domain.ChatRoom;
import com.simple.shopping.domain.ChatUser;
import com.simple.shopping.domain.User;
import com.simple.shopping.dto.ChatMessage;
import com.simple.shopping.service.ChatService;
import com.simple.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/chatrooms")
public class ChatController {

    @Autowired
    ChatService chatService;

    @Autowired
    UserService userService;

    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/rooms/{chatRoomId}")
    public void sendMessage(@DestinationVariable Long chatRoomId, ChatMessage message, Principal principal) throws Exception{
        //해당 방에 입장한 사람일 경우만 실행
        User user = userService.getUserByEmail(principal.getName());
        message.setName(user.getName());
        this.template.convertAndSend("/topic/rooms/"+chatRoomId, message);
    }

    @GetMapping
    public String charRooms(ModelMap modelMap){

        List<ChatRoom> list = chatService.getChatRooms();
        modelMap.put("list", list);

        return "chat/chatRooms";
    }

    @GetMapping(path = "/{id}")
    public String chatroom(Principal principal, @PathVariable(name = "id") Long id, ModelMap modelMap){

        User user = userService.getUserByEmail(principal.getName());
        ChatRoom chatRoom = chatService.getChatRoom(id);
        List<ChatUser> chatUserList = chatRoom.getChatUsers();

        boolean findChatUser = false;
        for(ChatUser chatUser : chatUserList){
            if(chatUser.getUser().getNo().equals(user.getNo())){
                break;
            }else{
                findChatUser = true;
                break;
            }
        }

        if(!findChatUser){
            ChatUser chatUser = new ChatUser();
            chatUser.setChatRoom(chatRoom);
            chatService.addChatUser(chatUser);
        }

        modelMap.addAttribute("chatRoom", chatRoom);
        return "chat/chatRoom";
    }

    @PostMapping
    public String create(Principal principal, @RequestParam(name = "title") String title){
        User user = userService.getUserByEmail(principal.getName());
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setTitle(title);

        ChatUser chatUser = new ChatUser();
        chatUser.setUser(user);
        chatUser.setChatRoom(chatRoom);

        chatService.addChatRoom(chatRoom);
        return "redirect:/chatrooms";
    }

    @GetMapping("/createform")
    public String create(){
        return "chat/chatForm";
    }





}
