package com.example.fridge_partner;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ThirdMainActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_third_main);

        dataList = new ArrayList<>();
        dataList.add("Refrigerated");
        dataList.add("whole milk\n" +
                "active yeast \n" +
                "2all-purpose flour\n" +
                "yellow cornmeal\n" +
                "brown sugar\n" +
                "kosher salt\n" +
                "unsalted butter, melted\n" +
                "large eggs\n" +
                "baking soda");
        dataList.add("Frozen");
        dataList.add("smoked salmon\n" +
                "ed onion, sliced thin\n" +
                "apple cider vinegar\n" +
                "sesame seed bagels\n" +
                "beef\n" +
                "lamb\n" +
                "Dijon mustard\n" +
                "honey\n" +
                "olive oil\n" +
                "ice cream\n" +
                "Mixed greens\n" +
                "cup capers\n" +
                "bean");

        // Get ListView instance

        listView = (ListView) this.findViewById(R.id.listView);
        // Create and set adapter
        CustomAdapter customAdapter = new CustomAdapter(dataList);
        listView.setAdapter(customAdapter);

    }

    private class CustomAdapter extends ArrayAdapter<String> {
        CustomAdapter(ArrayList<String> dataList) {
            super(ThirdMainActivity.this, R.layout.list_item_sub, R.id.sub_item_text, dataList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = super.getView(position, convertView, parent);

            // Set text color for sub items
            if (position % 2 != 0) {
                view.setBackgroundColor(getResources().getColor(android.R.color.white));
            }

            return view;
        }
    }
}