package com.example.fridge_partner;

import android.app.DatePickerDialog;
import android.content.Context;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import android.Manifest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.AlarmManager;

import androidx.appcompat.app.AlertDialog;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;

import com.blankj.utilcode.constant.TimeConstants;
import com.blankj.utilcode.util.MapUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.example.fridge_partner.adapter.FoodAdapter;
import com.example.fridge_partner.adapter.FridgeAdapter;
import com.example.fridge_partner.entity.FoodEntity;
import com.example.fridge_partner.entity.FridgeEntity;
import com.example.fridge_partner.model.FoodModelManager;

import java.util.Date;
import java.util.List;

import kotlin.TuplesKt;

public class ThirdMainActivity extends AppCompatActivity {

    public static final String FRIDGE_NAME = "FRIDGE_NAME";
    public static final int REFRIGERATE_TYPE = 1;
    public static final int FREEZE_TYPE = 2;
    private static final int REQUEST_CODE_POST_NOTIFICATIONS = 1;
    public static final String TIME_FORMAT = "yyyy/MMMM/dd";

    FridgeEntity entity;

    public static void startThirdMainActivity(Context context, FridgeEntity entity) {
        Intent intent = new Intent(context, ThirdMainActivity.class);
        intent.putExtra(FRIDGE_NAME, entity);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_main);
        entity = (FridgeEntity) getIntent().getSerializableExtra(FRIDGE_NAME);

        createNotificationChannel();
        initData();

        TextView tvFridgeName = findViewById(R.id.tvFridgeName);
        tvFridgeName.setText("Fridge Name:" + entity.getTitle());


        RecyclerView frozenRecyclerView = findViewById(R.id.frozenRecyclerView);
        frozenRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        frozenRecyclerView.setAdapter(refrigerateAdapter);


        RecyclerView freezeRecyclerView = findViewById(R.id.freezeRecyclerView);
        freezeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        freezeRecyclerView.setAdapter(freezeAdapter);

        Button mAddFoodButton = findViewById(R.id.addFrozenButton);
        Button addFreezeButton = findViewById(R.id.addFreezeButton);

        mAddFoodButton.setOnClickListener(v -> showFoodDialog(REFRIGERATE_TYPE));
        addFreezeButton.setOnClickListener(v -> showFoodDialog(FREEZE_TYPE));


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, REQUEST_CODE_POST_NOTIFICATIONS);
            }
        }

        new ItemTouchHelper(new DeleteItemTouchHelper(this, REFRIGERATE_TYPE)).attachToRecyclerView(frozenRecyclerView);
        new ItemTouchHelper(new DeleteItemTouchHelper(this, FREEZE_TYPE)).attachToRecyclerView(freezeRecyclerView);

    }

    List<FoodEntity> refrigerateData;
    List<FoodEntity> freezeData;
    private FoodAdapter refrigerateAdapter;
    private FoodAdapter freezeAdapter;

    private void initData() {
        refrigerateData = FoodModelManager.getModelsByTags(entity.getId(), REFRIGERATE_TYPE);
        freezeData = FoodModelManager.getModelsByTags(entity.getId(), FREEZE_TYPE);
        refrigerateAdapter = new FoodAdapter(refrigerateData);
        freezeAdapter = new FoodAdapter(freezeData);
    }


    public void removeFood(int position, int type) {
        Pair<FoodAdapter, List<FoodEntity>> value = getValue(type);
        if (position < value.second.size()) {
            FoodEntity foodEntity = value.second.get(position);
            FoodModelManager.deleteFood(foodEntity);
            value.second.remove(position);
            value.first.notifyItemRemoved(position);
        }
    }

    private void showFoodDialog(int type) {
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

        CalendarButton.setOnClickListener(v -> showDate(editTextDescription));

        // Set up the buttons
        builder.setPositiveButton("Create", (dialog, which) -> {
            handleFood(editTextName, editTextDescription, type);
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    private void handleFood(EditText editTextName, EditText editTextDescription, int type) {
        String foodName = editTextName.getText().toString();
        String expiryDate = editTextDescription.getText().toString();
        if (foodName.isEmpty()) foodName = "New Food";
        if (expiryDate.isEmpty()) expiryDate = "2024/12/31";
        Date date = TimeUtils.getDate(expiryDate, TimeUtils.getSafeDateFormat(TIME_FORMAT), 0, TimeConstants.DAY);
        FoodEntity model = new FoodEntity(date.getTime(), foodName, type, entity.getId());
        FoodModelManager.addFood(model);
        Pair<FoodAdapter, List<FoodEntity>> value = getValue(type);
        value.second.add(model);
        value.first.notifyItemInserted(value.second.size() - 1);
    }


    Pair<FoodAdapter, List<FoodEntity>> getValue(int type) {
        List<FoodEntity> models;
        FoodAdapter adapter;
        if (type == REFRIGERATE_TYPE) {
            models = refrigerateData;
            adapter = refrigerateAdapter;
        } else {
            models = freezeData;
            adapter = freezeAdapter;
        }
        return new Pair<>(adapter, models);
    }


    private void showDate(EditText editTextDescription) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(ThirdMainActivity.this, (view, year, month, dayOfMonth) -> {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, dayOfMonth);
            editTextDescription.setText(TimeUtils.date2String(calendar.getTime(), TimeUtils.getSafeDateFormat(TIME_FORMAT)));

        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        Calendar now = Calendar.getInstance();
        now.add(java.util.Calendar.YEAR, 1);
        Calendar day = Calendar.getInstance();
        now.add(java.util.Calendar.DAY_OF_YEAR, -1);
        datePickerDialog.getDatePicker().setMaxDate(now.getTime().getTime());
        datePickerDialog.getDatePicker().setMinDate(day.getTime().getTime());
        datePickerDialog.show();
    }

    public void showDatePickerDialog(FoodEntity foodItem) {
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

    private void showTimePickerDialog(Calendar calendar, FoodEntity foodItem) {
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, (view, selectedHour, selectedMinute) -> {
            calendar.set(Calendar.HOUR_OF_DAY, selectedHour);
            calendar.set(Calendar.MINUTE, selectedMinute);
            scheduleAlarm(calendar.getTimeInMillis(), foodItem);
        }, hour, minute, true);

        timePickerDialog.show();
    }

    private void scheduleAlarm(long timeInMillis, FoodEntity foodItem) {
        Intent intent = new Intent(this, AlarmReceiver.class);
        intent.putExtra("FoodName", foodItem.getName());
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, (int) timeInMillis, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, timeInMillis, pendingIntent);

        Toast.makeText(this, "Alarm set for " + foodItem.getName(), Toast.LENGTH_SHORT).show();
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


class DeleteItemTouchHelper extends ItemTouchHelper.SimpleCallback {

    private final ThirdMainActivity activity;
    private final int type;

    public DeleteItemTouchHelper(ThirdMainActivity activity, int type) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.activity = activity;
        this.type = type;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        activity.removeFood(viewHolder.getAbsoluteAdapterPosition(), type);
    }
}