package com.simple.shopping.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "chat_user")
@Getter
@Setter
public class ChatUser implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "chat_room_id")
    @JsonBackReference
    ChatRoom chatRoom;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    User user;

    // 헬퍼메소드
    public void setChatRoom(ChatRoom chatRoom){
        this.chatRoom = chatRoom;
        if(!chatRoom.getChatUsers().contains(this)){
            chatRoom.getChatUsers().add(this);
        }
    }

}
