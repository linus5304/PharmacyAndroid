package com.example.pharmaciedemo.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.pharmaciedemo.Adapters.CategoryAdapter;
import com.example.pharmaciedemo.AddCategoryActivity;
import com.example.pharmaciedemo.DrugItemActivity;
import com.example.pharmaciedemo.Model.Category;
import com.example.pharmaciedemo.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class CategoryFragment extends Fragment implements CategoryAdapter.OnCategoryListener {

    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;
    private CategoryAdapter categoryAdapter;
    private ArrayList<Category> categoryList;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("category");


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_category, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_category);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        categoryList = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(getContext(), categoryList, this);

        recyclerView.setAdapter(categoryAdapter);

        root.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                categoryList.clear();
                for(DataSnapshot snapshot1 :snapshot.getChildren()){
                    Category category = snapshot1.getValue(Category.class);
                    Log.d("TAG", category.toString());
                    categoryList.add(category);
                }
                categoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return view;
    }


    @Override
    public void onCategoryClick(int position) {
        categoryList.get(position);
        Intent intent = new Intent(getContext(), AddCategoryActivity.class);
        startActivity(intent);

    }
}