package com.example.freshnexdelivery;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    Context mContext;
    ArrayList<Product> productArrayList;
    private static final String TAG = "ProductAdapter";

    public ProductAdapter(Context mContext, ArrayList<Product> productArrayList) {
        this.mContext = mContext;
        this.productArrayList = productArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.custome_sumrry, parent, false);

        return new ViewHolder(view,viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.priceTextView.setText("" + productArrayList.get(position).getPrice());
        holder.nameTextView.setText("" + productArrayList.get(position).getName());
        holder.quantityTextView.setText("" + productArrayList.get(position).getQuantity() + "x" + productArrayList.get(position).getQuantity_Type());
        Log.d(TAG, "onBindViewHolder: "+Constant.Image_Base_URL + productArrayList.get(position).getImageUrl());
        Glide.with(mContext).load(Constant.Image_Base_URL + productArrayList.get(position).getImageUrl()).thumbnail(Glide.with(mContext).load(R.drawable.plroduct_place)).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, priceTextView, quantityTextView;
        ImageView imageView;

        public ViewHolder(View view, int viewType) {
            super(view);
            nameTextView = (TextView) view.findViewById(R.id.txt_title);
            priceTextView = (TextView) view.findViewById(R.id.txt_price);
            quantityTextView = (TextView) view.findViewById(R.id.txt_priceanditem);
            imageView = (ImageView) view.findViewById(R.id.productImageView);


        }
    }


}
