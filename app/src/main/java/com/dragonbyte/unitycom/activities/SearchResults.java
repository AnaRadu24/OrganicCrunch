package com.dragonbyte.unitycom.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.dragonbyte.unitycom.Product;
import com.dragonbyte.unitycom.R;
import com.dragonbyte.unitycom.User;
import com.dragonbyte.unitycom.adapters.RecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchResults extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerAdapter mAdapter;

    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    FirebaseAuth.AuthStateListener mAuthListener;

    DatabaseReference mDatabase;

    String product;

    ArrayList<Product> myUserList = new ArrayList<Product>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("In Search Results", "Creating activity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null) {

            Log.e("Bundle not null", "Getting references...");

            product = (String) bundle.get("PRODUCT_TO_SEARCH");

            mAuth = FirebaseAuth.getInstance();
            mAuthListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    currentUser = firebaseAuth.getCurrentUser();
                    if (currentUser != null) {
                        // User is signed in
                        Log.d("here", "onAuthStateChanged:signed_in:" + currentUser.getUid());

                        final DatabaseReference mDatabase;
                        mDatabase = FirebaseDatabase.getInstance().getReference();

                        Query usersWithProducts = mDatabase.child("products").child(product).limitToFirst(100);

                        usersWithProducts.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot userSnaphot : dataSnapshot.getChildren()) {
                                    Log.e("In JSON tree", "Users found.");
                                    Product prod = userSnaphot.getValue(Product.class);
                                    myUserList.add(myUserList.size(), prod);
                                    Log.e("In JSON tree", prod.getName());
                                }

                                if(myUserList.size() == 0)
                                    Toast.makeText(getBaseContext(), "We couldn't find any seller.", Toast.LENGTH_LONG).show();

                                mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
                                mLinearLayoutManager = new LinearLayoutManager(getBaseContext());
                                mRecyclerView.setLayoutManager(mLinearLayoutManager);

                                mAdapter = new RecyclerAdapter(myUserList);
                                mRecyclerView.setAdapter(mAdapter);

                                setRecyclerViewScrollListener();

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                Log.e("From Database", "While trying to recover queries", databaseError.toException());
                            }
                        });
                    }
                }

            };

        }

    }

    @Override
    public void onStart() {
        myUserList.clear();
        Log.e("In SearchResults", "Passes by onStart");
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onRestart() {
        super.onRestart();
    }

    private int getLastVisibleItemPosition() {
        return mLinearLayoutManager.findLastVisibleItemPosition();
    }

    private void setRecyclerViewScrollListener() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                Log.e("setRecyclerViewScroll", "onScrollStateChanged");
                super.onScrollStateChanged(recyclerView, newState);
                int totalItemCount = mRecyclerView.getLayoutManager().getItemCount();

                if(totalItemCount == getLastVisibleItemPosition() + 1) {
                    Log.e("setRecyclerViewScroll", "wants new user");
                    //request a user
                }
            }
        });
    }
}
