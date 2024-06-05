package com.example.fridge_partner.adapter;


// java/FoodAdapter.java

import static com.example.fridge_partner.ThirdMainActivity.TIME_FORMAT;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.TimeUtils;
import com.example.fridge_partner.R;
import com.example.fridge_partner.ThirdMainActivity;
import com.example.fridge_partner.entity.FoodEntity;

import java.util.Date;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    private final List<FoodEntity> foodItems;

    public FoodAdapter(List<FoodEntity> foodItems) {
        this.foodItems = foodItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FoodEntity foodItem = foodItems.get(position);
        holder.textViewFoodName.setText(foodItem.getName());
        holder.textViewExpiryDate.setText(TimeUtils.date2String(new Date(foodItem.getExpiryDate()), TimeUtils.getSafeDateFormat(TIME_FORMAT)));
        holder.buttonSetAlarm.setOnClickListener(v -> {
            Context context = holder.itemView.getContext();
            if (context instanceof ThirdMainActivity) {
                ThirdMainActivity activity = (ThirdMainActivity) context;
                activity.showDatePickerDialog(foodItem);
            }
            Toast.makeText(v.getContext(), "Alarm Set for: ", Toast.LENGTH_LONG).show();

        });
    }

    @Override
    public int getItemCount() {
        return foodItems.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewFoodName, textViewExpiryDate;
        Button buttonSetAlarm;

        ViewHolder(View itemView) {
            super(itemView);
            textViewFoodName = itemView.findViewById(R.id.textViewFoodName);
            textViewExpiryDate = itemView.findViewById(R.id.textViewExpiryDate);
            buttonSetAlarm = itemView.findViewById(R.id.buttonSetAlarm);
        }
    }


}
