package com.example.fridge_partner.fragment;

import static com.example.fridge_partner.ThirdMainActivity.REFRIGERATE_TYPE;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SnackbarUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.fridge_partner.NoteActivity;
import com.example.fridge_partner.R;
import com.example.fridge_partner.adapter.NoteAdapter;
import com.example.fridge_partner.entity.NoteEntity;
import com.example.fridge_partner.model.NoteModelManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NoteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NoteFragment extends Fragment {


    public static NoteFragment newInstance() {
        NoteFragment fragment = new NoteFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes, container, false);
        FloatingActionButton actionButton = view.findViewById(R.id.floatingActionButton);
        actionButton.setOnClickListener(v -> showTitleDialog());
        initView(view);
        return view;
    }

    List<NoteEntity> models;
    NoteAdapter noteAdapter;

    private void initView(View view) {
        RecyclerView mList = view.findViewById(R.id.noteRecyclerView);
        mList.setLayoutManager(new LinearLayoutManager(getContext()));
        models = NoteModelManager.getModels();
        noteAdapter = new NoteAdapter(models);
        new ItemTouchHelper(new NoteDeleteItemTouchHelper(this)).attachToRecyclerView(mList);
        mList.setAdapter(noteAdapter);
    }


    void showTitleDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Input note title");
        final EditText input = new EditText(getContext());
        builder.setView(input);
        builder.setPositiveButton("OK", (dialog, which) -> {
            String content = input.getText().toString();
            if (StringUtils.isEmpty(content)) {
                ToastUtils.showLong("title is empty");
                return;
            }
            NoteActivity.startNoteActivity(content, getActivity());

        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        builder.show();
    }


    public void doChange(NoteEntity entity) {
        if (!models.isEmpty()) {
            models.add(entity);
            noteAdapter.notifyItemChanged(models.size() - 1);
        }
    }

    public void remoteNote(int position) {
        NoteEntity entity = models.get(position);
        models.remove(entity);
        entity.delete();
        noteAdapter.notifyItemRemoved(position);
    }
}

