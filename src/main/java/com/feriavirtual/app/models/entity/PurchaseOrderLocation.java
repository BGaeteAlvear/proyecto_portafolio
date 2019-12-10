package com.feriavirtual.app.models.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "purchase_order_location")
public class PurchaseOrderLocation implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "SEQ_PURCHASE_ORDER_LOCATION")
    @SequenceGenerator(name = "SEQ_PURCHASE_ORDER_LOCATION",allocationSize = 1,sequenceName = "SEQ_PURCHASE_ORDER_LOCATION")
    private Long id;

    private int positionLocation;
    private int rowLocation;
    private int columnLocation;
    private int roomLocation;
    private int code;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPositionLocation() {
        return positionLocation;
    }

    public void setPositionLocation(int positionLocation) {
        this.positionLocation = positionLocation;
    }

    public int getRowLocation() {
        return rowLocation;
    }

    public void setRowLocation(int rowLocation) {
        this.rowLocation = rowLocation;
    }

    public int getColumnLocation() {
        return columnLocation;
    }

    public void setColumnLocation(int columnLocation) {
        this.columnLocation = columnLocation;
    }

    public int getRoomLocation() {
        return roomLocation;
    }

    public void setRoomLocation(int roomLocation) {
        this.roomLocation = roomLocation;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
