package com.example.fridge_partner.fragment;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class NoteDeleteItemTouchHelper extends ItemTouchHelper.SimpleCallback {

    private final NoteFragment fragment;

    public NoteDeleteItemTouchHelper(NoteFragment fragment) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.fragment = fragment;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        fragment.remoteNote(viewHolder.getAbsoluteAdapterPosition());
    }
}
