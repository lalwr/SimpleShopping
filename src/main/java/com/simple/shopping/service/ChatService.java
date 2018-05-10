package com.simple.shopping.service;

import com.simple.shopping.domain.ChatRoom;
import com.simple.shopping.domain.ChatUser;
import java.util.List;

public interface ChatService {
    public List<ChatRoom> getChatRooms();
    public void addChatRoom(ChatRoom chatRoom);
    public void addChatUser(ChatUser chatUser);
    public ChatRoom getChatRoom(Long id);

}
