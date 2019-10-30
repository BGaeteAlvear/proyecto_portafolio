package com.feriavirtual.app.models.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShoppingCart {


    private int id = 0;
    private Long userId;
    private Long productAvailableId;
    private int quantity;
    private List availableProducts;;

    public ShoppingCart(){};

    public ShoppingCart(List availableProducts) {
        this.availableProducts = availableProducts;
    }

    public ShoppingCart(Long userId, Long productAvailableId, int quantity){
        this.id++;
        this.userId = userId;
        this.productAvailableId = productAvailableId;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductAvailableId() {
        return productAvailableId;
    }

    public void setProductAvailableId(Long productAvailableId) {
        this.productAvailableId = productAvailableId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List getAvailableProducts() {
        return availableProducts;
    }

    public void setAvailableProducts(List availableProducts) {
        this.availableProducts = availableProducts;
    }

}
