package com.simple.shopping.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "product_image")
@Getter
@Setter
@ToString

public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;
    private String originFileName;
    private String savePath;
    private String saveName;

    public String getImagePath(){
        return savePath + saveName;
    }
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_no")
    Product product;
}
