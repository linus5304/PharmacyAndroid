package com.example.pharmaciedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pharmaciedemo.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {

    TextView email, name, status;
    ImageView profileImage, logoutImage;
    FirebaseUser fUser;
    String profileId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        email = findViewById(R.id.prof_email);
        name = findViewById(R.id.prof_username);
        status = findViewById(R.id.prof_status);
        profileImage = findViewById(R.id.prof_image);
        logoutImage = findViewById(R.id.prof_logout);

        fUser = FirebaseAuth.getInstance().getCurrentUser();
        if(fUser.getUid() == null){
            profileId = "";
        }else{
            profileId = fUser.getUid();
        }

        getSupportActionBar().setTitle("My Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        logoutImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FirebaseAuth.getInstance().signOut();
//                Toast.makeText(ProfileActivity.this, "Logged Out!", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(ProfileActivity.this, StartActivity.class));
//            }
//        });


        getUserInfo();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void getUserInfo() {
        FirebaseDatabase.getInstance().getReference().child("Users").child(profileId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                if(user.getImageUrl().equals("default")){
                    profileImage.setImageResource(R.mipmap.ic_launcher);
                }else{
                    Picasso.get().load(user.getImageUrl()).into(profileImage);
                }

                email.setText(user.getUsername());
                name.setText(user.getUsername());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}