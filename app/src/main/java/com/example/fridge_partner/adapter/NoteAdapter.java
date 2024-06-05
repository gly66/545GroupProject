package com.example.fridge_partner.adapter;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fridge_partner.R;
import com.example.fridge_partner.RecipeDetailActivity;
import com.example.fridge_partner.entity.NoteEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private final List<NoteEntity> notes;


    public NoteAdapter(List<NoteEntity> notes) {
        this.notes = notes;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NoteEntity foodItem = notes.get(position);
        holder.noteTitle.setText(foodItem.getTitle());
        holder.noteContent.setText(foodItem.getContent());
        holder.cardView.setCardBackgroundColor(foodItem.getBgColor());


    }

    @Override
    public int getItemCount() {
        return notes.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView noteTitle, noteContent;
        CardView cardView;

        ViewHolder(View itemView) {
            super(itemView);
            noteTitle = itemView.findViewById(R.id.tvNoteTitle);
            noteContent = itemView.findViewById(R.id.tvNoteContent);
            cardView = itemView.findViewById(R.id.cardView);
//            buttonSetAlarm = itemView.findViewById(R.id.buttonSetAlarm);
        }
    }


}
