package com.example.fridge_partner;


// java/FoodAdapter.java
import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
            // Placeholder for set alarm functionality here !!

            Toast.makeText(v.getContext(), "Alarm Set for: ",Toast.LENGTH_LONG).show();

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

//    private void showDatePickerDialog() {
//        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
//            Calendar.set(year, month, dayOfMonth);
//            showTimePickerDialog();
//        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
//        datePickerDialog.show();
//    }
//
//    private void showTimePickerDialog() {
//        TimePickerDialog timePickerDialog = new TimePickerDialog(this, (view, hourOfDay, minute) -> {
//            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
//            calendar.set(Calendar.MINUTE, minute);
//            calendar.set(Calendar.SECOND, 0);
//            setAlarm(calendar.getTimeInMillis());
//        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
//        timePickerDialog.show();
//    }
//
//    private void setAlarm(long timeInMillis) {
//        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//        Intent intent = new Intent(this, AlarmReceiver.class);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
//        if (alarmManager != null) {
//            alarmManager.setExact(AlarmManager.RTC_WAKEUP, timeInMillis, pendingIntent);
//        }
//        Toast.makeText(this, "Alarm Set for: " + calendar.getTime(), Toast.LENGTH_LONG).show();
//    }

}
