package com.dragonbyte.unitycom.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.dragonbyte.unitycom.R;
import com.dragonbyte.unitycom.User;
import com.dragonbyte.unitycom.adapters.TabsAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.R.attr.name;
import static java.security.AccessController.getContext;


public class UnityComMainActivity extends AppCompatActivity  {

    TabsAdapter adapter;


    public static String profileName, profileAddress, profileTelephone, listProducts;

    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    DatabaseReference mDatabase;
    FirebaseAuth.AuthStateListener mAuthListener;

    public static User user;

    ActionBarDrawerToggle mDrawerToggle;
    ListView mListView;
    int board = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.e("In Main Activity", "here");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unitycom_main_activity_layout);

        //   adapter = new DrawerAdapter(getSupportFragmentManager());

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                currentUser = firebaseAuth.getCurrentUser();
                if (currentUser != null) {
                    // User is signed in
                    Log.d("here", "onAuthStateChanged:signed_in:" + currentUser.getUid());

                    mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(currentUser.getUid());
                    mDatabase.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            user = dataSnapshot.getValue(User.class);

                            profileName = user.getFirstName() + " " + user.getSecondName();
                            profileAddress = user.getAddress();
                            profileTelephone = user.getTelephone();
                            listProducts = user.getProducts();

                            initToolbar();

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }
        };


    }

    @Override
    public void onStart() {
        Log.e("In UnityComMainActivity", "Passes by OnStart");
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {

        Log.e("In UnityComMainActivity", "Passes by OnStop");
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


    private void initToolbar() {
        Log.e("In UnityComMainActivity", "initializing toolbar");
        final ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.main_tab_layout);
        tabLayout.removeAllTabs();


        tabLayout.addTab(tabLayout.newTab().setText("Search"));
        tabLayout.addTab(tabLayout.newTab().setText("My Account"));
        adapter = new TabsAdapter(getSupportFragmentManager(), 1);
        viewPager.setAdapter(adapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getText().equals("Search")) {
                    adapter.setBoard(1);
                    Log.e("here", tab.getText().toString());
                    board = 1;
                } else if (tab.getText().equals("My Account")){
                    adapter.setBoard(2);
                    board = 2;
                }
                else {
                    board = 3;
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mToolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.icons));


        mToolbar.setNavigationIcon(null);
        mToolbar.hideOverflowMenu();
        mToolbar.dismissPopupMenus();
    }


}