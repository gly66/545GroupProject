package com.example.fridge_partner;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {

    private static final int SPLASH_SCREEN_TIMEOUT = 3000; // 3 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView logo = findViewById(R.id.logo);
        TextView slogan = findViewById(R.id.slogan);
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        logo.startAnimation(fadeIn);
        slogan.startAnimation(fadeIn);

        // Handler to start the MainActivity after the timeout
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation fadeOut = AnimationUtils.loadAnimation(SplashScreenActivity.this, R.anim.fade_out);
                logo.startAnimation(fadeOut);
                slogan.startAnimation(fadeOut);

                fadeOut.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {}

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Intent intent = new Intent(SplashScreenActivity.this, SecMainActivity.class);
                        startActivity(intent);
                        finish(); // Close the SplashScreenActivity
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {}
                });
            }
        }, SPLASH_SCREEN_TIMEOUT);
    }
}