package com.sky.musiclearn;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import com.sky.musiclearn.ui.SplashView;

public class SplashActivity extends Activity {
    SplashView splashView;
    private static final int SPLASH_DISPLAY_TIME = 2000;//2s
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        splashView = new SplashView(this);
        setContentView(splashView);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_splash_enter,R.anim.anim_splash_exit);
                finish();
            }
        },SPLASH_DISPLAY_TIME);
    }

}
