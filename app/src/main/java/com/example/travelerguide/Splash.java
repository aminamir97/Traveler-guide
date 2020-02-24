package com.example.travelerguide;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.HorizontalScrollView;

import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);



        new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Intent intent  = new Intent(Splash.this , MainActivity.class);
                startActivity(intent);

            }
        }.start();
    }
}
