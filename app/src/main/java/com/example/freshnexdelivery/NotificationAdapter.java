package com.example.freshnexdelivery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {
    Context mContext;
    ArrayList<GuidlineData> productImageArrayList;

    public NotificationAdapter(Context mContext, ArrayList<GuidlineData> productImageArrayList) {
        this.mContext = mContext;
        this.productImageArrayList = productImageArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.notification_item, parent, false);

        return new ViewHolder(view,viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageResource(productImageArrayList.get(position).getImage());
        holder.textViewGuide1.setText(productImageArrayList.get(position).getText1());
        holder.textViewGuide2.setText(productImageArrayList.get(position).getText2());


    }

    @Override
    public int getItemCount() {
        return productImageArrayList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewGuide1, textViewGuide2;

        public ViewHolder(View view, int viewType) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.imageGuide);
            textViewGuide1 = (TextView) view.findViewById(R.id.textGuide1);
            textViewGuide2 = (TextView) view.findViewById(R.id.textGuide2);


        }
    }
}
