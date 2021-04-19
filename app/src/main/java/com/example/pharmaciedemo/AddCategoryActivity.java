package com.example.pharmaciedemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

import java.util.HashMap;

public class AddCategoryActivity extends AppCompatActivity {
    private static final int IMAGE_REQUEST = 1000;
    private ImageView imageView;
    private MaterialButton button;
    private TextInputEditText name, description;
    private Uri imageUri;
    private String imageUrl;



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        Toolbar toolbar = findViewById(R.id.toolbar_add_cat);


//        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Comments");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        button = findViewById(R.id.btn_cat_add);
        name = findViewById(R.id.cat_name);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload();
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void upload() {
        ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Uploading...");


                    String txtName = name.getText().toString();
                    if(txtName.length() == 0){
                        name.setError("Enter a value");
                    }else{
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("category");
                        String catId = ref.push().getKey();

                        HashMap<String, Object> map = new HashMap<>();
                        map.put("carId", catId);
                        map.put("name", txtName);

                        ref.child(catId).setValue(map);

                        pd.dismiss();
                        startActivity(new Intent(AddCategoryActivity.this, MainActivity.class));
                        finish();
                        Toast.makeText(AddCategoryActivity.this, "Category succefully added", Toast.LENGTH_SHORT).show();
                    }


    }





}