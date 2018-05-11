package com.simple.shopping.service.impl;

import com.simple.shopping.domain.ChatRoom;
import com.simple.shopping.domain.ChatUser;
import com.simple.shopping.repository.ChatRoomRepository;
import com.simple.shopping.repository.ChatUserRepository;
import com.simple.shopping.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService{

    @Autowired
    ChatRoomRepository chatRoomRepository;

    @Autowired
    ChatUserRepository chatUserRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ChatRoom> getChatRooms() {
        return chatRoomRepository.findAll();
    }

    @Override
    @Transactional
    public void addChatRoom(ChatRoom chatRoom) {
        chatRoomRepository.save(chatRoom);
    }

    @Override
    @Transactional
    public void addChatUser(ChatUser chatUser) {
        chatUserRepository.save(chatUser);
    }

    @Override
    @Transactional(readOnly = true)
    public ChatRoom getChatRoom(Long id) {
        return chatRoomRepository.getOne(id);
    }
}
