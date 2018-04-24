package com.simple.shopping.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "order_product")
@Getter
@Setter
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    private int amount;

    private String status;

    @ManyToOne
    @JoinColumn(name = "order_no")
    private Order order;

    public void setOrder(Order order){
        this.order = order;
        if(!order.getOrderProducts().contains(this)){
            order.getOrderProducts().add(this);
        }
    }

    @ManyToOne
    @JoinColumn(name = "product_no")
    private Product product;

    public void setProduct(Product product){
        this.product = product;
        if(!product.getOrderProducts().contains(this)){
            product.getOrderProducts().add(this);
        }
    }
}
