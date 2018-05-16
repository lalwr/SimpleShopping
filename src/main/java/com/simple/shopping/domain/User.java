package com.simple.shopping.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User implements Serializable{
    public User(){
        regdate = LocalDateTime.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;
    private String name;
    private String email;
    private String address;
    private String phone;
    @JsonIgnore
    private String password;
    private LocalDateTime regdate;
    private String use = "Y";

    @OneToMany(mappedBy = "user" ,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<UserRole> roles = new ArrayList<>();

    // 헬퍼 메소드. User에 UserRole을 추가할때 사용한다.
    public void addUserRole(UserRole role){
        this.roles.add(role);
        if(role.getUser() != this){
            role.setUser(this);
        }

    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Bill> biils = new ArrayList<>();

    public void addBill(Bill bill){
        this.biils.add(bill);
        if(bill.getUser()!=this){
            bill.setUser(this);
        }
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Cart> carts = new ArrayList<>();

    public void addCart(Cart cart){
        this.carts.add(cart);
        if(cart.getUser() != this){
            cart.setUser(this);
        }
    }

    @OneToMany(mappedBy = "user", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JsonManagedReference
    private List<UserConnection> userConnections = new ArrayList<>();

    public void addUserConnection(UserConnection userConnection){
        this.userConnections.add(userConnection);
        if(userConnection.getUser() != this){
            userConnection.setUser(this);
        }
    }

}