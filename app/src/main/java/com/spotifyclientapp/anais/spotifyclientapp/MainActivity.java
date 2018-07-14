package com.spotifyclientapp.anais.spotifyclientapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.spotifyclientapp.anais.spotifyclientapp.authentication.ConnectActivity;

public class MainActivity  extends AppCompatActivity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                startNextActivity();
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void startNextActivity() {
        startActivity(new Intent(getApplicationContext(), ConnectActivity.class));
        finish();
    }
}
