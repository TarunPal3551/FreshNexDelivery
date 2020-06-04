package com.example.freshnexdelivery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {
    Context mContext;

    public NotificationAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.notification_item, parent, false);

        return new ViewHolder(view,viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return 10;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View view, int viewType) {
            super(view);


        }
    }
}
