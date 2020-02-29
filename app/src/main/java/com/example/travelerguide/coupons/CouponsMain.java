package com.example.travelerguide.coupons;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.media.MediaDrm;
import android.os.Bundle;

import com.example.travelerguide.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CouponsMain extends AppCompatActivity {
    private DatabaseReference mCustomerDatabase;
    private ArrayList<CouponModel> list = new ArrayList<CouponModel>();
    private RecyclerView recyclerView;
    CouponsAdapter adapter;
    String mProfileImageUrl;
    ProgressDialog progressDialog;
    String name = null,phone= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupons_main);


        mCustomerDatabase = FirebaseDatabase.getInstance().getReference().child("coupons");
        recyclerView = findViewById(R.id.recycler_view);
        adapter = new CouponsAdapter(list , getApplicationContext());


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading....");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(false);
        progressDialog.show();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
       // recyclerData();

        list.clear();
        mCustomerDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    CouponModel coupon = dsp.getValue(CouponModel.class);
                    list.add(coupon);
                }

                adapter.notifyDataSetChanged();
                progressDialog.dismiss();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        list.clear();

    }
}
