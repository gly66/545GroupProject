package com.example.fridge_partner;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class CreateFridgePopWin extends PopupWindow {

    private Context mContext;
    private View view;
    private Button btn_create, btn_reset, buttonIncreaseForzen, buttonDecreaseForzen, buttonIncreaseFreezing, buttonDecreaseFreezing;
    private EditText editTextForzen, editTextFreezing, editTextName, editTextDescription;

    public CreateFridgePopWin(Activity mContext) {
        this.mContext = mContext;
        this.view = LayoutInflater.from(mContext).inflate(R.layout.create_fridge, null);

        // Initialize components
        editTextName = view.findViewById(R.id.fridgename);
        editTextDescription = view.findViewById(R.id.description);
        editTextForzen = view.findViewById(R.id.editTextForzen);
        editTextFreezing = view.findViewById(R.id.editTextFreezing);
        buttonIncreaseForzen = view.findViewById(R.id.buttonIncreaseForzen);
        buttonDecreaseForzen = view.findViewById(R.id.buttonDecreaseForzen);
        buttonIncreaseFreezing = view.findViewById(R.id.buttonIncreaseFreezing);
        buttonDecreaseFreezing = view.findViewById(R.id.buttonDecreaseFreezing);
        btn_create = view.findViewById(R.id.createButton);
        btn_reset = view.findViewById(R.id.resetButton);

        // Setup listeners for increment and decrement
        setupIncrementDecrementListeners(buttonIncreaseForzen, buttonDecreaseForzen, editTextForzen);
        setupIncrementDecrementListeners(buttonIncreaseFreezing, buttonDecreaseFreezing, editTextFreezing);

        // Set button listeners
        btn_create.setOnClickListener(v -> {
            // Implement create functionality
            // This could involve collecting all input data and sending it to a server or local database
        });

        btn_reset.setOnClickListener(v -> {
            resetFields();
        });

        // Set properties of the popup window
        setOutsideTouchable(true);
        setContentView(this.view);
        setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        setWidth((int) (mContext.getResources().getDisplayMetrics().widthPixels * 0.95));
        setFocusable(true);


    }

    private void setupIncrementDecrementListeners(Button increase, Button decrease, EditText editText) {
        increase.setOnClickListener(v -> {
            int currentValue = Integer.parseInt(editText.getText().toString());
            editText.setText(String.valueOf(currentValue + 1));
        });
        decrease.setOnClickListener(v -> {
            int currentValue = Integer.parseInt(editText.getText().toString());
            if (currentValue > 0) {
                editText.setText(String.valueOf(currentValue - 1));
            }
        });
    }

    private void resetFields() {
        editTextName.setText("");
        editTextDescription.setText("");
        editTextForzen.setText("0");
        editTextFreezing.setText("0");
    }
}
