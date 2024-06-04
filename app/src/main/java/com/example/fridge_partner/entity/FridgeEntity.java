package com.example.fridge_partner.entity;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

public class FridgeEntity extends LitePalSupport implements Serializable {
    @Column(unique = true)
    private Long id;
    private String title;
    private String desc;
    private int refrigerateRow;
    private int freezeRow;

    public FridgeEntity(String title, String desc, int refrigerateRow, int freezeRow) {
        this.title = title;
        this.desc = desc;
        this.refrigerateRow = refrigerateRow;
        this.freezeRow = freezeRow;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getRefrigerateRow() {
        return refrigerateRow;
    }

    public void setRefrigerateRow(int refrigerateRow) {
        this.refrigerateRow = refrigerateRow;
    }

    public int getFreezeRow() {
        return freezeRow;
    }

    public void setFreezeRow(int freezeRow) {
        this.freezeRow = freezeRow;
    }
}
