package com.example.pharmaciedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pharmaciedemo.Adapters.PagerAdapter;
import com.example.pharmaciedemo.Adapters.ViewPagerAdapter;
import com.example.pharmaciedemo.Fragments.CategoryFragment;
import com.example.pharmaciedemo.Fragments.DrugsFragment;
import com.example.pharmaciedemo.Model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private CategoryFragment categoryFragment;
    private DrugsFragment drugsFragment;
    private BottomNavigationView bottomNavigationView;
    private Fragment selectedFragment;
    TextView username;
    PagerAdapter pagerAdapter;
    String uid;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.nav_view);
        username = findViewById(R.id.nav_head_name);





        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close );

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_category:
                        selectedFragment = new CategoryFragment();
                        break;
                    case R.id.nav_drugs:
                        selectedFragment = new DrugsFragment();
                        break;

                }
                if(selectedFragment != null){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                }
                return true;
            }
        });

        Bundle intent = getIntent().getExtras();

        if(intent == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CategoryFragment()).commit();
            bottomNavigationView.setSelectedItemId(R.id.nav_category);
        }


        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();


        FirebaseDatabase.getInstance().getReference().child("Users").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
//                username.setText(user.getUsername());
                Log.d("my user", user.toString());
                Toast.makeText(MainActivity.this, user.getUsername(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


//        tabLayout.setupWithViewPager(viewPager);
//
//
//        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, tabLayout.getTabCount());
//        viewPager.setAdapter(pagerAdapter);
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                viewPager.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
//
//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));




        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.add_category:
                        Intent intent = new Intent(MainActivity.this, AddCategoryActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.add_drug:
                        Intent intent1 = new Intent(MainActivity.this, AddDrugActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.logout:
                        FirebaseAuth.getInstance().signOut();
                        Toast.makeText(MainActivity.this, "Logged Out!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, StartActivity.class));
                    case R.id.profile:
                        Intent intent2 = new Intent(MainActivity.this, ProfileActivity.class);
                        startActivity(intent2);
                }
                return true;
            }
        });





    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}