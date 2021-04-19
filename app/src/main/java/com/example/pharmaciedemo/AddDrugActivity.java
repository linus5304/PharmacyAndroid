package com.example.pharmaciedemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pharmaciedemo.Fragments.DrugsFragment;
import com.example.pharmaciedemo.Model.Category;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

import java.util.ArrayList;
import java.util.HashMap;

public class AddDrugActivity extends AppCompatActivity {

    private static final int IMAGE_REQUEST = 1000;
    private TextInputEditText itemName, itemCode, itemCostPrice, itemSellingPrice, itemQuantity, itemCategory;
    private ImageView itemImage;
    MaterialButton addButton;
    Uri imageUri;
    private String imageUrl;
    ArrayList<String> categoryList;

    String catName;
    String catId;

    AutoCompleteTextView autoCompleteTextView;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_drug);

        Toolbar toolbar = findViewById(R.id.drug_toolbar);

        getSupportActionBar().setTitle("Add Drug");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        itemName = findViewById(R.id.item_name);
        itemCode = findViewById(R.id.item_code);
        itemCostPrice = findViewById(R.id.item_cost_price);
        itemSellingPrice = findViewById(R.id.item_selling_price);
        itemQuantity = findViewById(R.id.item_quantity);
        itemImage = findViewById(R.id.item_image);
        addButton = findViewById(R.id.add_item);


        Intent intent = getIntent();
         catName = intent.getStringExtra("catName");
         catId = intent.getStringExtra("catId");



        autoCompleteTextView = findViewById(R.id.autoComplete);
        autoCompleteTextView.setText(catName);


        itemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImage();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadItem();
            }
        });





    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    private void uploadItem() {
        ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Uploading Item ....");
        if(imageUri != null){
            StorageReference filepath = FirebaseStorage.getInstance().getReference().child("drugs").child(System.currentTimeMillis() + "."+ getFileExtension(imageUri));
            StorageTask uploadTask = filepath.putFile(imageUri);
            uploadTask.continueWithTask(new Continuation() {
                @Override
                public Object then(@NonNull Task task) throws Exception {
                    if(!task.isSuccessful()){
                        throw task.getException();
                    }
                    return filepath.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    Uri downloadUri  = task.getResult();
                    imageUrl = downloadUri.toString();


                    String txtName = itemName.getText().toString();
                    String txtCode = itemCode.getText().toString();
                    String txtCost = itemCostPrice.getText().toString();
                    String txtSell = itemSellingPrice.getText().toString();
                    String txtQuantity = itemQuantity.getText().toString();
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("drugs");
                    String itemId = ref.push().getKey();

                    HashMap<String, Object> map = new HashMap<>();
                    map.put("name", txtName);
                    map.put("code", txtCode);
                    map.put("costPrice", txtCost);
                    map.put("SellingPrice", txtSell);
                    map.put("Quantity", txtQuantity);
                    map.put("itemId", itemId);
                    map.put("imageUrl", imageUrl);
                    map.put("catId", catId);

                    ref.child(itemId).setValue(map);

                    pd.dismiss();
                    startActivity(new Intent(AddDrugActivity.this, DrugsFragment.class));
                    finish();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(AddDrugActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    private String getFileExtension(Uri uri) {
        return MimeTypeMap.getSingleton().getExtensionFromMimeType(this.getContentResolver().getType(uri));
    }
    private void openImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(intent, IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == IMAGE_REQUEST){
            imageUri = data.getData();
            itemImage.setImageURI(imageUri);
        }
    }


}