package com.feriavirtual.app.models.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Data
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "SEQ_PRODUCTS")
    @SequenceGenerator(name = "SEQ_PRODUCTS",allocationSize = 1,sequenceName = "SEQ_PRODUCTS")

    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    @NotEmpty
    private String image;
    private Boolean status;

    @Column(name = "created_at")
    @DateTimeFormat(pattern = "dd-MM-yyyy’T’hh:mm:")

    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    public Product(){
        this.createdAt= new Date();
        this.status = true;
    }

}
