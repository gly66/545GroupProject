package com.example.fridge_partner.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.blankj.utilcode.util.AppUtils;
import com.bumptech.glide.Glide;
import com.example.fridge_partner.R;
import com.example.fridge_partner.RecipeDetailActivity;
import com.example.fridge_partner.entity.RecipeEntity;
import com.example.fridge_partner.model.RecipeModelManager;

import java.util.List;

public class RecipeFragment extends Fragment {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<RecipeEntity> dataList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe, container, false);

        // Initialize data
        dataList = RecipeModelManager.getData();

        listView = view.findViewById(R.id.listView);
        CustomAdapter customAdapter = new CustomAdapter(dataList);
        listView.setAdapter(customAdapter);

        TextView webLinkButton = view.findViewById(R.id.tvGoogleSearch);
        webLinkButton.setOnClickListener(v -> {
            Uri uri = Uri.parse("https://www.simplyrecipes.com/recipe-collections-5119362");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });
        return view;
    }

    // Custom adapter class to handle sub items
    private class CustomAdapter extends ArrayAdapter<RecipeEntity> {
        CustomAdapter(List<RecipeEntity> dataList) {
            super(requireContext(), R.layout.list_item_sub, R.id.sub_item_text, dataList);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = super.getView(position, convertView, parent);
            RecipeEntity recipeEntity = RecipeModelManager.getData().get(position);
            ImageView view2 = view.findViewById(R.id.recipePicture);
            TextView textView = view.findViewById(R.id.tvSubText);
            textView.setText(recipeEntity.getTitle());
            Glide.with(parent.getContext()).load(recipeEntity.getPicture()).into(view2);
            view.setOnClickListener(v -> RecipeDetailActivity.startRecipeDetailActivity(v.getContext(), recipeEntity));
            return view;
        }
    }
}
