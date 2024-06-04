package com.example.fridge_partner.model;

import com.example.fridge_partner.entity.FridgeEntity;
import com.example.fridge_partner.entity.NoteEntity;

import org.litepal.LitePal;

import java.util.List;

public class FridgeModelManager {

    public static List<FridgeEntity> getModels() {
        return LitePal.findAll(FridgeEntity.class);
    }

    public static List<FridgeEntity> addFridge(FridgeEntity entity) {
        boolean save = entity.save();
        return LitePal.findAll(FridgeEntity.class);
    }

    public static void deleteFridge(FridgeEntity entity) {
        entity.delete();
    }


}
