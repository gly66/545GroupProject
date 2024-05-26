package com.example.fridge_partner;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.widget.Toast;

import android.Manifest;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.content.pm.PackageManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.AlarmManager;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Build;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ThirdMainActivity extends AppCompatActivity {

    private RecyclerView mFoodRecyclerView;
    private FoodAdapter mFoodAdapter;
    private ArrayList<FoodAdapter.FoodItem> mFoodList;
    private Button mAddFoodButton;
    private static final int REQUEST_CODE_POST_NOTIFICATIONS = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_main);
        createNotificationChannel();

        mFoodList = new ArrayList<>();
        mFoodAdapter = new FoodAdapter(mFoodList,this);

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
        // Check and request notification permission for Android 13 and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, REQUEST_CODE_POST_NOTIFICATIONS);
            }
        }


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
                showDate(editTextDescription);
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

    private void showDate(EditText editTextDescription) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(ThirdMainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);

                SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault());
                String selectedDate = dateFormat.format(calendar.getTime());
                editTextDescription.setText(selectedDate);

            }
        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    public void showDatePickerDialog(FoodAdapter.FoodItem foodItem) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, selectedYear, selectedMonth, selectedDay) -> {
            calendar.set(Calendar.YEAR, selectedYear);
            calendar.set(Calendar.MONTH, selectedMonth);
            calendar.set(Calendar.DAY_OF_MONTH, selectedDay);
            showTimePickerDialog(calendar, foodItem);
        }, year, month, day);

        datePickerDialog.show();
    }

    private void showTimePickerDialog(Calendar calendar, FoodAdapter.FoodItem foodItem) {
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, (view, selectedHour, selectedMinute) -> {
            calendar.set(Calendar.HOUR_OF_DAY, selectedHour);
            calendar.set(Calendar.MINUTE, selectedMinute);
            scheduleAlarm(calendar.getTimeInMillis(), foodItem);
        }, hour, minute, true);

        timePickerDialog.show();
    }

    private void scheduleAlarm(long timeInMillis, FoodAdapter.FoodItem foodItem) {
        Intent intent = new Intent(this, AlarmReceiver.class);
        intent.putExtra("FoodName", foodItem.getFoodName());
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, (int) timeInMillis, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, timeInMillis, pendingIntent);

        Toast.makeText(this, "Alarm set for " + foodItem.getFoodName(), Toast.LENGTH_SHORT).show();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "AlarmChannel";
            String description = "Channel for Alarm Manager";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("alarmChannel", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}