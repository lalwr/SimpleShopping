package com.simple.shopping.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="order")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    private String name;
    private String address;
    private String phone;
    private LocalDateTime regdate;

    @ManyToOne
    @JoinColumn(name="user_no")
    private User user;

    public void setUser(User user){
        this.user = user;
        if(!user.getOrders().contains(this)){
            user.getOrders().add(this);
        }
    }

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    private List<OrderProduct> orderProducts = new ArrayList<>();

    public void addOrderProduct(OrderProduct orderProduct){
        this.orderProducts.add(orderProduct);
        if(orderProduct.getOrder() != this){
            orderProduct.setOrder(this);
        }
    }
}
