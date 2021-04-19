package com.example.pharmaciedemo.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pharmaciedemo.AddDrugActivity;
import com.example.pharmaciedemo.Model.Category;
import com.example.pharmaciedemo.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Category> mCategory;
    private OnCategoryListener onCategoryListener;

    public CategoryAdapter(Context mContext, ArrayList<Category> mCategory, OnCategoryListener onCategoryListener) {
        this.mContext = mContext;
        this.mCategory = mCategory;
        this.onCategoryListener = onCategoryListener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.category_item, parent, false);
        return new ViewHolder(view, onCategoryListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = mCategory.get(position);
        holder.catName.setText("Anti Biotics");

        holder.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AddDrugActivity.class);
                intent.putExtra("catName", category.getCatName());
                intent.putExtra("catId", category.getCatId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCategory.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView catName;
        OnCategoryListener onCategoryListener;
        public Button addButton;

        public ViewHolder(@NonNull View itemView, OnCategoryListener onCategoryListener) {
            super(itemView);

            catName = itemView.findViewById(R.id.cat_name);
            addButton = itemView.findViewById(R.id.btn_add_drug);
            this.onCategoryListener = onCategoryListener;

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onCategoryListener.onCategoryClick(getAdapterPosition());
        }
    }

    public interface OnCategoryListener{
        void onCategoryClick(int position);
    }

}
