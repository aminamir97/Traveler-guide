package com.example.travelerguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.HorizontalScrollView;

public class MainActivity extends AppCompatActivity {
    HorizontalScrollView scroll;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        scroll = findViewById(R.id.scroll);


        new CountDownTimer(1000, 5000) {
            @Override
            public void onTick(long millisUntilFinished) {
                scroll.scrollTo(3,0);



            }

            @Override
            public void onFinish() {
                start();

            }
        }.start();




    }
}
