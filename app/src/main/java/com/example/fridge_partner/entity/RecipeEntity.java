package com.example.fridge_partner.entity;

import java.io.Serializable;

public class RecipeEntity implements Serializable {
    Long id;
    String picture;
    String title;
    String desc;


    public RecipeEntity(Long id, String picture, String title, String desc) {
        this.id = id;
        this.picture = picture;
        this.title = title;
        this.desc = desc;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}



