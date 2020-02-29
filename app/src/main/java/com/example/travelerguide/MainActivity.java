package com.example.travelerguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    HorizontalScrollView scroll;
    int i=0;
    ImageView profilepic;

    FirebaseAuth firebaseAuth;
    TextView username;

    GridLayout gridLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        gridLayout = findViewById(R.id.menu);
        scroll = findViewById(R.id.scroll);
        firebaseAuth = firebaseAuth.getInstance();
        profilepic = findViewById(R.id.profPic);
        username = findViewById(R.id.username);

        //Toast.makeText(this,firebaseAuth.getInstance().getCurrentUser().getPhotoUrl().toString(),Toast.LENGTH_LONG).show();
       // Log.e("phot",firebaseAuth.getCurrentUser().getPhotoUrl().toString());

        Glide.with(this)
                .load(firebaseAuth.getCurrentUser().getPhotoUrl())
                .into(profilepic);

        username.setText(firebaseAuth.getCurrentUser().getDisplayName());











    }

    public void signout(View view) {
        firebaseAuth.getInstance().signOut();
        Intent intent  = new Intent(MainActivity.this , Login.class);
        startActivity(intent);


    }


    public void ReccomendationClick(View view) {
    }

    public void CouponsClick(View view) {
    }

    public void AddSpotClick(View view) {
    }

    public void exchnageCurrencyClick(View view) {

        Intent intent = new Intent(MainActivity.this,CurrencyExchange.class);
        startActivity(intent);
    }
}
