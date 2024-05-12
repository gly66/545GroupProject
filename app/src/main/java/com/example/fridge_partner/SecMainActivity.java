package com.example.fridge_partner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.fridge_partner.databinding.ActivityMain2Binding;
import com.example.fridge_partner.databinding.ActivityMainBinding;

import java.util.HashMap;
import java.util.Map;

public class SecMainActivity extends AppCompatActivity {
    ActivityMain2Binding binding;

    private final Map<Integer, Fragment> fragments = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupFragments();
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {


            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_layout, fragments.get(item.getItemId()))
                    .commit();

            return true;
        });

    }

    private void setupFragments() {
        fragments.put(R.id.home, new HomeFragment());
        fragments.put(R.id.recipe, new RecipeFragment());
        fragments.put(R.id.user, new UserFragment());

//         Initially display the first fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, fragments.get(R.id.home))
                .commit();
    }


}