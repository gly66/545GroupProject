package com.example.fridge_partner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.fridge_partner.entity.RecipeEntity;

public class RecipeDetailActivity extends Activity {


    public static void startRecipeDetailActivity(Context context, RecipeEntity entity) {
        Intent intent = new Intent(context, RecipeDetailActivity.class);
        intent.putExtra("model", entity);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        TextView content = findViewById(R.id.recipeContent);
        TextView name = findViewById(R.id.tvRecipeName);
        RecipeEntity entity = (RecipeEntity) getIntent().getSerializableExtra("model");
        if (entity != null) {
            content.setText(entity.getDesc());
            name.setText(entity.getTitle());
        }
    }
}
