package com.example.pharmaciedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DrugItemActivity extends AppCompatActivity {
    private static final int IMAGE_REQUEST = 1000;
    private TextInputEditText itemName, itemSellingPrice, itemQuantity, itemCategory;
    private ImageView itemImage;
    MaterialButton sellButton, deleteButton ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_item);

        Toolbar toolbar = findViewById(R.id.new_drug_toolbar);
        Intent intent = getIntent();
        getSupportActionBar().setTitle(intent.getStringExtra("name"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        itemName = findViewById(R.id.new_item_name);
        itemImage = findViewById(R.id.new_item_image);
        itemSellingPrice = findViewById(R.id.new_item_selling_price);
        sellButton = findViewById(R.id.update_item);
        deleteButton = findViewById(R.id.delete_item);


        itemName.setText(intent.getStringExtra("name"));
        itemSellingPrice.setText(intent.getStringExtra("sellPrice"));
        Picasso.get().load(intent.getStringExtra("imageUrl")).into(itemImage);

//        deleteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FirebaseDatabase.getInstance().getReference().child()
//            }
//        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}