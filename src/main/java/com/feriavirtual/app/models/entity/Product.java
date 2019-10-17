package com.feriavirtual.app.models.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "SEQ_PRODUCTS")
    @SequenceGenerator(name = "SEQ_PRODUCTS",allocationSize = 1,sequenceName = "SEQ_PRODUCTS")

    private Long id;
    private String name;
    private String description;
    private String image;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;

    public Product(){
        this.createdAt= new Date();

    }

}
