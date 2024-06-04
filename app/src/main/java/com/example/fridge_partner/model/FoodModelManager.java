package com.example.fridge_partner.model;

import com.example.fridge_partner.entity.FoodEntity;
import com.example.fridge_partner.entity.FridgeEntity;

import org.litepal.LitePal;

import java.util.List;

public class FoodModelManager {

    public static List<FoodEntity> getModels() {
        return LitePal.findAll(FoodEntity.class);
    }

    public static List<FoodEntity> getModelsByTags(Long parentId, int type) {
        return LitePal.where("parentId=" + parentId + " and type=" + type).find(FoodEntity.class);
    }

    public static List<FoodEntity> addFood(FoodEntity entity) {
        boolean save = entity.save();
        return LitePal.findAll(FoodEntity.class);
    }

    public static void deleteFood(FoodEntity entity) {
        entity.delete();
    }


}
