package com.simple.shopping.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="bill")
@Getter
@Setter
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    private String name;
    private String address;
    private String phone;
    private String password;
    private String status;
    private LocalDateTime regdate;

    @ManyToOne
    @JoinColumn(name="user_no")
    private User user;

    public void setUser(User user){
        this.user = user;
        if(!user.getBiils().contains(this)){
            user.getBiils().add(this);
        }
    }

    @OneToMany(mappedBy = "bill", fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    private List<OrderProduct> orderProducts = new ArrayList<>();

    public void addOrderProduct(OrderProduct orderProduct){
        this.orderProducts.add(orderProduct);
        if(orderProduct.getBill() != this){
            orderProduct.setBill(this);
        }
    }

    public int getTotalPrice(){
        int totalPrice = 0;
        for(OrderProduct orderProduct: orderProducts){
            totalPrice+=orderProduct.getPrice()*orderProduct.getAmount();
        }
        return totalPrice;
    }
}