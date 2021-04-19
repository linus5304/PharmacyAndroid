package com.example.pharmaciedemo.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pharmaciedemo.Adapters.DrugAdapter;
import com.example.pharmaciedemo.DrugItemActivity;
import com.example.pharmaciedemo.Model.Category;
import com.example.pharmaciedemo.Model.Drug;
import com.example.pharmaciedemo.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.hendraanggrian.appcompat.widget.SocialAutoCompleteTextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class DrugsFragment extends Fragment implements DrugAdapter.OnDrugListener {
    private RecyclerView recyclerView;
    ArrayList<Drug> drugArrayList;
    private SocialAutoCompleteTextView searchBar;
    List<String> itemIds;
    DrugAdapter drugAdapter;
    DatabaseReference drugRef = FirebaseDatabase.getInstance().getReference();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drugs, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_drugs);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        searchBar = view.findViewById(R.id.search_bar);

        drugArrayList = new ArrayList<>();
        itemIds = new ArrayList<>();
        drugAdapter = new DrugAdapter(getContext(), drugArrayList, this);
        recyclerView.setAdapter(drugAdapter);
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchDrug(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        getDrugs();

        return view;

    }



    private void getDrugs() {
        drugRef.child("drugs").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1: snapshot.getChildren()){
                    Drug drug = snapshot1.getValue(Drug.class);
                    Log.d("####drug", drug.toString());
                    drugArrayList.add(drug);
                }
                drugAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }




    private void searchDrug(String s){
        Query query = FirebaseDatabase.getInstance().getReference().child("drugs").orderByChild("name").startAt(s).endAt(s + "\uf8ff");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                drugArrayList.clear();
                for(DataSnapshot snapshot1: snapshot.getChildren()){
                    Drug Drug = snapshot1.getValue(Drug.class);
                    drugArrayList.add(Drug);
                }
                drugAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onDrugItemClick(int position) {
        Drug drug = drugArrayList.get(position);
        Intent intent = new Intent(getContext(), DrugItemActivity.class);
        String name = drug.getItemName();
        String imageUrl = drug.getImageUrl();
        String sellPrice = drug.getItemSellingPrice();

        intent.putExtra("name", name);
        intent.putExtra("imageUrl", imageUrl);
        intent.putExtra("sellPrice", sellPrice);

        startActivity(intent);
    }


}