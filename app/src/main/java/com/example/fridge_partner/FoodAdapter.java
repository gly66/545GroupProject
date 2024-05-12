package com.example.fridge_partner;


// java/FoodAdapter.java
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    private List<FoodItem> foodItems;

    public FoodAdapter(List<FoodItem> foodItems) {
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
        FoodItem foodItem = foodItems.get(position);
        holder.textViewFoodName.setText(foodItem.getFoodName());
        holder.textViewExpiryDate.setText(foodItem.getExpiryDate());
        holder.buttonSetAlarm.setOnClickListener(v -> {
            // Placeholder for set alarm functionality
        });
    }

    @Override
    public int getItemCount() {
        return foodItems.size();
    }


    public void removeFood(int position) {
        if (position < foodItems.size()) {
            foodItems.remove(position);
            notifyItemRemoved(position);
        }
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

    public static class FoodItem {
        private String foodName;
        private String expiryDate;

        public FoodItem(String foodName, String expiryDate) {
            this.foodName = foodName;
            this.expiryDate = expiryDate;
        }

        public String getFoodName() {
            return foodName;
        }

        public String getExpiryDate() {
            return expiryDate;
        }
    }

}
