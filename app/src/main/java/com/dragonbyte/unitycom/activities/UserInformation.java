package com.dragonbyte.unitycom.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.dragonbyte.unitycom.R;
import com.dragonbyte.unitycom.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;


public class UserInformation extends AppCompatActivity {

    private TextView setEmail, setName, setPhone, setAddress, setProducts;
    private String email, name, phone, address, products;

    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    DatabaseReference mDatabase;
    FirebaseAuth.AuthStateListener mAuthListener;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_user_information);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {

            Log.e("Bundle not null", "Getting references...");

            email = (String) bundle.get("USER");

            setEmail = (TextView) findViewById(R.id.farmerEmail);
            setName = (TextView) findViewById(R.id.FarmerName);
            setPhone = (TextView) findViewById(R.id.FarmerTelephone);
            setAddress = (TextView) findViewById(R.id.FarmAddress);
            setProducts = (TextView) findViewById(R.id.products);

            mAuth = FirebaseAuth.getInstance();
            mAuthListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    currentUser = firebaseAuth.getCurrentUser();
                    if (currentUser != null) {
                        // User is signed in
                        Log.d("here", "onAuthStateChanged:signed_in:" + currentUser.getUid());

                        mDatabase = FirebaseDatabase.getInstance().getReference();

                        Query searchUser = mDatabase.child("users").orderByChild("email").equalTo(email);

                        searchUser.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for(DataSnapshot userSnapshot: dataSnapshot.getChildren()) {
                                    user = userSnapshot.getValue(User.class);
                                    Log.e("Found the user", user.getFirstName() + " " + user.getSecondName());
                                }

                                setName.setText(user.getFirstName() + " " + user.getSecondName());
                                setEmail.setText(user.getEmail());
                                setAddress.setText(user.getAddress());
                                setProducts.setText(user.getProducts());
                                setPhone.setText(user.getTelephone());

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                }
            };

        }
    }

    @Override
    public void onStart() {
        Log.e("In UserInformation", "Passes by onStart");
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

}
