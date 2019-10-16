package com.feriavirtual.app.models.entity;

public class ProductAvailable {

    private Long id;
    private String name;
    private String description;
    private int stock;
    private String image;

    public ProductAvailable(Long id, String name, String description, int stock, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}