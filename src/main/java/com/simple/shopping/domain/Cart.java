package com.simple.shopping.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table (name = "cart")
@Getter
@Setter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    @Column(name = "amount")
    private int amount;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;

    public void setUser(User user){
        this.user = user;
        if(!user.getCarts().contains(this)){
            user.getCarts().add(this);
        }
    }

    @ManyToOne
    @JoinColumn(name ="product_no")
    private Product product;

    public void setProduct(Product product){
        this.product = product;
        if(!product.getCarts().contains(this)){
           product.getCarts().add(this);
        }
    }

}
