package com.example.fridge_partner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.fridge_partner.entity.NoteEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NoteActivity extends Activity {
    public static final String NOTE_TAG = "NOTE_TAG";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        initView();
    }

    void initView() {
        NoteEntity entity = new NoteEntity();
        String stringExtra = getIntent().getStringExtra(NOTE_TAG);
        TextView tvNoteTitle = findViewById(R.id.tvNoteTitle);
        ImageView ivFinish = findViewById(R.id.ivFinish);
        EditText etContent = findViewById(R.id.etContent);
        tvNoteTitle.setText(stringExtra);
        ivFinish.setOnClickListener(v -> {
            entity.setTitle(stringExtra);
            entity.setContent(etContent.getText().toString());
            int color=getColor();
            entity.setBgColor(color);
            entity.save();
            Intent intent = new Intent();
            intent.putExtra("model", entity);
            setResult(1000, intent);
            finish();
        });
    }

    Integer getColor() {
        List<Integer> colors = new ArrayList<>();
        colors.add(ContextCompat.getColor(this, R.color.c1));
        colors.add(ContextCompat.getColor(this, R.color.c2));
        colors.add(ContextCompat.getColor(this, R.color.c3));
        colors.add(ContextCompat.getColor(this, R.color.c4));
        colors.add(ContextCompat.getColor(this, R.color.c5));
        return colors.get(new Random().nextInt(colors.size() - 1));
    }


    public static void startNoteActivity(String name, Activity context) {
        Intent intent = new Intent(context, NoteActivity.class);
        intent.putExtra(NOTE_TAG, name);
        context.startActivityForResult(intent, 1000);
    }
}
