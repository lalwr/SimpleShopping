package com.simple.shopping.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="product")
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    private String name;

    private int amount;

    private int price;

    private LocalDateTime regdate;

    @OneToMany(mappedBy = "order_product",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<OrderProduct> orderProducts = new ArrayList<>();

    public void setProduct(OrderProduct orderProduct){
        this.orderProducts.add(orderProduct);
        if(orderProduct.getProduct() != this){
            orderProduct.setProduct(this);
        }
    }

    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL ,fetch = FetchType.EAGER )
    List<Cart> carts = new ArrayList<>();

    public void setProduct(Cart cart){
        this.carts.add(cart);
        if(cart.getProduct() != this){
            cart.setProduct(this);
        }
    }

    @ManyToOne
    @JoinColumn(name = "category_no")
    private Category category;

    public void addCategory(Category category){
        this.category = category;
        if(!category.getProducts().contains(this)){
            category.getProducts().add(this);
        }
    }

}
