package com.example.fridge_partner.entity;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

public class FoodEntity extends LitePalSupport implements Serializable {

    @Column(unique = true)
    private Long id;
    private Long expiryDate;
    private String name;
    private int type;
    private Long parentId;


    public FoodEntity(Long expiryDate, String name, int type, Long parentId) {
        this.expiryDate = expiryDate;
        this.name = name;
        this.type = type;
        this.parentId = parentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Long expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
