package com.example.freshnexdelivery;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class DeliveryAdapter extends RecyclerView.Adapter<DeliveryAdapter.ViewHolder> {
    Context mContext;
    ArrayList<OrderData> orderDataArrayList;

    public DeliveryAdapter(Context mContext, ArrayList<OrderData> orderDataArrayList) {
        this.mContext = mContext;
        this.orderDataArrayList = orderDataArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.delivered_items, parent, false);

        return new ViewHolder(view,viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, OrderDetails.class);
                mContext.startActivity(intent);
            }
        });
        holder.textViewViewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, OrderDetails.class);
                mContext.startActivity(intent);
            }
        });
        if (orderDataArrayList.get(position).getStatus().equals("assigned")) {
            holder.textViewStatus.setText("Pending");
        } else {
            holder.textViewStatus.setText("" + orderDataArrayList.get(position).getStatus());
        }
        holder.textViewPaymentMode.setText("" + orderDataArrayList.get(position).getpMethod());
        holder.textViewPrice.setText("₹" + orderDataArrayList.get(position).getTotal());
        holder.textViewDeliveryDate.setText("Delivered On" + orderDataArrayList.get(position).getDdate());
        holder.orderIdTextView.setText("Order Id #" + orderDataArrayList.get(position).getOid());

    }


    @Override
    public int getItemCount() {
        return orderDataArrayList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView orderIdTextView,
                textViewDeliveryDate,
                textViewStatus,
                textViewPaymentMode,
                textViewPrice, textViewViewDetails;

        public ViewHolder(View view, int viewType) {
            super(view);
            orderIdTextView = (TextView) view.findViewById(R.id.textViewId);
            textViewViewDetails = (TextView) view.findViewById(R.id.textViewDetails);
            textViewPaymentMode = (TextView) view.findViewById(R.id.textViewPaymentMode);
            textViewStatus = (TextView) view.findViewById(R.id.textViewPaymentStatus);
            textViewPrice = (TextView) view.findViewById(R.id.textViewPrice);
            textViewDeliveryDate = (TextView) view.findViewById(R.id.textViewDeliveryDate);


        }
    }
}
