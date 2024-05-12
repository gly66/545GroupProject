package com.example.fridge_partner;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ThirdMainActivity extends AppCompatActivity {

    private RecyclerView mFoodRecyclerView;
    private FoodAdapter mFoodAdapter;
    private ArrayList<FoodAdapter.FoodItem> mFoodList;
    private Button mAddFoodButton;

    private String preDate,Date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_main);

        mFoodList = new ArrayList<>();
        mFoodAdapter = new FoodAdapter(mFoodList);

        mFoodRecyclerView = findViewById(R.id.frozenRecyclerView);
        mFoodRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mFoodRecyclerView.setAdapter(mFoodAdapter);

        mAddFoodButton = findViewById(R.id.addFrozenButton);
        mAddFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                addFood("New Food ","Expiry date");
                showDialog();
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false; // No move operations
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                removeFood(viewHolder.getAbsoluteAdapterPosition());
            }
        }).attachToRecyclerView(mFoodRecyclerView);
    }

    private void addFood(String food,String expiryDate) {
        mFoodList.add(new FoodAdapter.FoodItem(food, expiryDate));
        mFoodAdapter.notifyItemInserted(mFoodList.size() - 1);
    }

    private void removeFood(int position) {
        if (position < mFoodList.size()) {
            mFoodList.remove(position);
            mFoodAdapter.notifyItemRemoved(position);
        }
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter Details");

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.create_food, null);
        builder.setView(dialogView);

        // Find the EditText fields
        EditText editTextName = dialogView.findViewById(R.id.editTextName);
        EditText editTextDescription = dialogView.findViewById(R.id.editTextDescription);
        Button CalendarButton = dialogView.findViewById(R.id.button);

        CalendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate();
                editTextDescription.setText(Date);
            }
        });

        // Set up the buttons
        builder.setPositiveButton("Create", (dialog, which) -> {
            String foodName = editTextName.getText().toString();
            String expiryDate = editTextDescription.getText().toString();
            if(foodName.isEmpty())    foodName = "New Food";
            if(expiryDate.isEmpty())    expiryDate = "2024/12/31";
            addFood(foodName,expiryDate);
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    private void showDate() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(ThirdMainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);

                SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault());
                String selectedDate = dateFormat.format(calendar.getTime());

                preDate = Date;
                Date = selectedDate;
            }
        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

}