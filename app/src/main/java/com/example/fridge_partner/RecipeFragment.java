package com.example.fridge_partner;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class RecipeFragment extends Fragment {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> dataList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe, container, false);

        // Initialize data
        dataList = new ArrayList<>();
        dataList.add("Waffle");
        dataList.add("1 3/4 cups whole milk\n" +
                "2 1/4 teaspoon (7g) active yeast (or 1 package, not fast acting or instant)\n" +
                "2 cups (280g) all-purpose flour\n" +
                "1/4 cup (40g) yellow cornmeal\n" +
                "1 tablespoon brown sugar\n" +
                "1 teaspoon kosher salt\n" +
                "1/2 cup unsalted butter, melted\n" +
                "2 large eggs\n" +
                "1/2 teaspoon baking soda");
        dataList.add("Smoked salmon brunch salad");
        dataList.add("12 sheets of MSC certified smoked salmon\n" +
                "1/2 a red onion, sliced thin\n" +
                "1/4 cup apple cider vinegar\n" +
                "2 sesame seed bagels, sliced into medallions\n" +
                "4 large eggs\n" +
                "1 clove garlic, minced\n" +
                "1 tbsp Dijon mustard\n" +
                "1 tsp honey\n" +
                "1/4 cup olive oil\n" +
                "1/2 tsp salt\n" +
                "142 g Mixed greens\n" +
                "1/4 cup capers\n" +
                "Salt and pepper to taste");
        dataList.add("Chinese Seafood and Tomato Hotpot");
        dataList.add("7oz (200g) of MSC certified halibut\n" +
                "3.5oz (100g) MSC certified Arctic surf clams (or other shellfish such as scallops or mussels)\n" +
                "3.5oz (100g) MSC certified cold water shrimps\n" +
                "\n" +
                "You’ll  also need…\n" +
                "3 cups (750ml) chicken stock\n" +
                "1/4 cup (50ml) vegetable oil\n" +
                "1 fresh red chilli\n" +
                "2 cloves of fresh garlic\n" +
                "2 slices of fresh ginger\n" +
                "7oz (200g) peeled fresh tomatoes (diced)\n" +
                "1tbsp of tomato paste\n" +
                "½ a small onion\n" +
                "4 slices of fresh lemon\n" +
                "Rice or noodles to accompany\n" +
                "Salt to taste");

        // Get ListView instance
        listView = view.findViewById(R.id.listView);

        // Create and set adapter
        CustomAdapter customAdapter = new CustomAdapter(dataList);
        listView.setAdapter(customAdapter);

        // Button click listener
        Button webLinkButton = view.findViewById(R.id.weblink);
        webLinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://translate.google.com/";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                intent.setPackage("com.android.chrome");

                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), "Please install Chrome to view this page.", Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }

    // Custom adapter class to handle sub items
    private class CustomAdapter extends ArrayAdapter<String> {
        CustomAdapter(ArrayList<String> dataList) {
            super(requireContext(), R.layout.list_item_sub, R.id.sub_item_text, dataList);
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
