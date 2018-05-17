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

    private String imagePath;

    private String name;

    private int price;

    private int amount;

    @ManyToOne
    @JoinColumn(name = "bill_no")
    private Bill bill;

    public void setBill(Bill bill){
        this.bill = bill;
        if(!bill.getOrderProducts().contains(this)){
            bill.getOrderProducts().add(this);
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