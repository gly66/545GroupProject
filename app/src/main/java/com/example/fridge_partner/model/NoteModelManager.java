package com.example.fridge_partner.model;

import com.example.fridge_partner.entity.NoteEntity;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class NoteModelManager {

   public  static List<NoteEntity> getModels() {
        List<NoteEntity> allNotes = LitePal.findAll(NoteEntity.class);
        return allNotes;
    }
}
