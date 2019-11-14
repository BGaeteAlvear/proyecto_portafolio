package com.feriavirtual.app.models.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import java.io.Serializable;

@Entity
@Data
@Table(name="categories")
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "SEQ_CATEGORIES")
    @SequenceGenerator(name = "SEQ_CATEGORIES",allocationSize = 1,sequenceName = "SEQ_CATEGORIES")
    private Long Id;

    @NotEmpty
    private String name;

    @NotEmpty
    @Lob
    private String description;

    private String image;


    public Category() {

    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public Long getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Long id) {
        Id = id;
    }
}



