package com.example.fridge_partner;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.fridge_partner.adapter.BottomViewAdapter;
import com.example.fridge_partner.databinding.ActivityMain2Binding;
import com.example.fridge_partner.entity.NoteEntity;
import com.example.fridge_partner.fragment.HomeFragment;
import com.example.fridge_partner.fragment.NoteFragment;
import com.example.fridge_partner.fragment.RecipeFragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SecMainActivity extends AppCompatActivity {
    ActivityMain2Binding binding;


    //    private final Map<Integer, Fragment> fragments = new HashMap<>();
    private final List<Fragment> fragments2 = new ArrayList<>();
    private final List<Integer> ids = new ArrayList<>();
    NoteFragment noteFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupFragments();
        setAdapter();
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            ViewPager viewPager = binding.getRoot().findViewById(R.id.viewPage);
            int i = ids.indexOf(item.getItemId());
            viewPager.setCurrentItem(i);
            return true;
        });

    }

    private void setupFragments() {
        fragments2.add(new HomeFragment());
        ids.add(R.id.home);
        fragments2.add(new RecipeFragment());
        ids.add(R.id.recipe);
        noteFragment = NoteFragment.newInstance();
        fragments2.add(noteFragment);
        ids.add(R.id.user);
    }


    private void setAdapter() {
        ViewPager viewPager = binding.getRoot().findViewById(R.id.viewPage);
        BottomViewAdapter adapter = new BottomViewAdapter(getSupportFragmentManager(), fragments2);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(fragments2.size());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 && resultCode == 1000) {
            if (data != null) {
                NoteEntity entity = (NoteEntity) data.getSerializableExtra("model");
                noteFragment.doChange(entity);
            }
        }
    }
}

