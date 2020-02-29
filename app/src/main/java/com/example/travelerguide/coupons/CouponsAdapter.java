package com.example.travelerguide.coupons;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.travelerguide.R;

import java.util.List;

public class CouponsAdapter extends RecyclerView.Adapter<CouponsAdapter.MyViewHolder    > {
    public List<CouponModel> list;
    private RelativeLayout linearLayout;



    Context context;


    public CouponsAdapter(List<CouponModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.coupon_item, parent, false);

        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        CouponModel couponModel = list.get(position);

        holder.sname.setText(couponModel.getName());
        holder.scity.setText(couponModel.getCity());
        holder.scode.setText(couponModel.getCode());

        Glide.with(context).load(couponModel.getImage()).into(holder.imageView);


        //click listener handling

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView sname, scity,scode;
        ImageView imageView;


        public MyViewHolder(View view) {
            super(view);
            sname = (TextView) view.findViewById(R.id.name);
            scity  = view.findViewById(R.id.city);
            scode  = view.findViewById(R.id.code);
            imageView = view.findViewById(R.id.img);
           // linearLayout = view.findViewById(R.id.clickRecyclerView);

        }

    }


}
