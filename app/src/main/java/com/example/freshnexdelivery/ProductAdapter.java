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


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    Context mContext;
    ArrayList<Product> productArrayList;

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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, OrderDetails.class);
                mContext.startActivity(intent);
            }
        });
        holder.nameTextView.setText("" + productArrayList.get(position).getName());
        holder.quantityTextView.setText("" + productArrayList.get(position).getQuantity() + "x" + productArrayList.get(position).getQuantity_Type());
    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, priceTextView, quantityTextView;

        public ViewHolder(View view, int viewType) {
            super(view);
            nameTextView = (TextView) view.findViewById(R.id.txt_title);
            priceTextView = (TextView) view.findViewById(R.id.txt_price);
            quantityTextView = (TextView) view.findViewById(R.id.txt_priceanditem);


        }
    }
}
