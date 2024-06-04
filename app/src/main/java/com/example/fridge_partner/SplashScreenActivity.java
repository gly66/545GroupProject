package com.example.fridge_partner;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.view.animation.AnimationUtils;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.SPUtils;
import com.example.fridge_partner.model.FridgeModelManager;

public class SplashScreenActivity extends AppCompatActivity {

    private static final int SPLASH_SCREEN_TIMEOUT = 3000; // 3 seconds
    private static final String NOTIFICATION_TAG = "NOTIFICATION_TAG3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView logo = findViewById(R.id.logo);
        TextView slogan = findViewById(R.id.slogan);
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        logo.startAnimation(fadeIn);
        slogan.startAnimation(fadeIn);

        boolean exist = SPUtils.getInstance().getBoolean(NOTIFICATION_TAG, false);
        if (!exist) {
            AlertDialog longClickDialog = new AlertDialog.Builder(this)
                    .setTitle("Tips")
                    .setMessage("Please go to the Settings screen to enable the notification permission")
                    .setPositiveButton("ok", (dialog, which) -> {
                        SPUtils.getInstance().put(NOTIFICATION_TAG, true);
                        finishAndStart();
                        AppUtils.launchAppDetailsSettings();
                    }).show();
            longClickDialog.setCancelable(false);
            longClickDialog.getButton(AlertDialog.BUTTON_POSITIVE)
                    .setTextColor(ContextCompat.getColor(this, R.color.teal_200));
        } else {
            new Handler().postDelayed(() -> {
                Animation fadeOut = AnimationUtils.loadAnimation(SplashScreenActivity.this, R.anim.fade_out);
                logo.startAnimation(fadeOut);
                slogan.startAnimation(fadeOut);

                fadeOut.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        finishAndStart();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });
            }, SPLASH_SCREEN_TIMEOUT);
        }
        // Handler to start the MainActivity after the timeout

    }


    void finishAndStart() {
        Intent intent = new Intent(SplashScreenActivity.this, SecMainActivity.class);
        startActivity(intent);
        finish();
    }
}