package com.example.pharmaciedemo.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pharmaciedemo.AddDrugActivity;
import com.example.pharmaciedemo.Model.Drug;
import com.example.pharmaciedemo.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DrugAdapter extends RecyclerView.Adapter<DrugAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Drug> drugList;
    private OnDrugListener onDrugListener;

    public DrugAdapter(Context mContext, ArrayList<Drug> drugList, OnDrugListener onDrugListener) {
        this.mContext = mContext;
        this.drugList = drugList;
        this.onDrugListener = onDrugListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.drug_item, parent, false);
        return new ViewHolder(view, onDrugListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Drug drug = drugList.get(position);
        holder.name.setText(drug.getItemName());
        Picasso.get().load(drug.getImageUrl()).into(holder.imageUrl);
        holder.Quantity.setText(drug.getQuantity());
        holder.SellingPrice.setText(drug.getItemSellingPrice());
    }


    @Override
    public int getItemCount() {
        return drugList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        OnDrugListener onDrugListener;
        public TextView name, Quantity;
        public TextView SellingPrice;
        public ImageView imageUrl;

        public ViewHolder(@NonNull View itemView, OnDrugListener onDrugListener) {
            super(itemView);
            this.onDrugListener = onDrugListener;

            name = itemView.findViewById(R.id.drug_name);
            SellingPrice = itemView.findViewById(R.id.drug_price);
            Quantity = itemView.findViewById(R.id.drug_quantity);
            imageUrl = itemView.findViewById(R.id.drug_image);

            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            onDrugListener.onDrugItemClick(getAdapterPosition());
        }
    }
    public interface OnDrugListener{
        void onDrugItemClick(int position);
    }
}
