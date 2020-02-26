package com.example.travelerguide;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.HorizontalScrollView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;


public class Splash extends AppCompatActivity {
    public FirebaseAuth firebaseAuth;
    Intent intent;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        firebaseAuth = FirebaseAuth.getInstance();


        //firebaseAuth.getInstance().signOut();
       //  Toast.makeText(Splash.this,firebaseAuth.getInstance().getCurrentUser().getDisplayName(), android.widget.Toast.LENGTH_LONG).show();



        new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }






            @Override
            public void onFinish() {

                if(!firebaseAuth.getCurrentUser().equals(null))
                     intent  = new Intent(Splash.this , MainActivity.class);
                else
                     intent  = new Intent(Splash.this , Login.class);



                startActivity(intent);



            }
        }.start();
    }
}
