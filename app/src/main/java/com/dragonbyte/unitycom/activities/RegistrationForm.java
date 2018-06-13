package com.dragonbyte.unitycom.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dragonbyte.unitycom.R;
import com.dragonbyte.unitycom.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RegistrationForm extends MainActivity {

    private boolean type;
    private static final String TAG = "Registration";

    private EditText mPassword;
    private EditText mUser;
    private EditText firstName;
    private EditText familyName;
    private EditText phoneNumber;
    private EditText address;

    private Button Costumer;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_activity);

        mUser = (EditText) findViewById(R.id.getRegistrationEmail);
        mPassword = (EditText) findViewById(R.id.getRegistrationPassword);
        firstName = (EditText) findViewById(R.id.getFirstName);
        familyName = (EditText) findViewById(R.id.getFamilyName);
        phoneNumber = (EditText) findViewById(R.id.getTelephone);
        address = (EditText) findViewById(R.id.getAddress);

        Costumer = (Button) findViewById(R.id.chooseCostumer);

        mAuth = FirebaseAuth.getInstance();

        Costumer.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String email;
                        String password;

                        type = false;
                        email = mUser.getText().toString();
                        password = mPassword.getText().toString();

                        createAccount(email, password);


                    }
                }
        );
    }



    private void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);

        if(!validateForm())
            return;

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        //if not
                        if (!task.isSuccessful()) {
                            Toast.makeText(RegistrationForm.this, "Registration failed!", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(RegistrationForm.this, "Registration successful!", Toast.LENGTH_SHORT).show();


                            String fname, sname, phone, Email, Password, ad;

                            fname = firstName.getText().toString();
                            sname = familyName.getText().toString();
                            phone = phoneNumber.getText().toString();
                            Email = mUser.getText().toString();
                            Password = mPassword.getText().toString();
                            ad = address.getText().toString();

                            User user = new User(fname, sname, Email, Password, phone, ad);
                            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

                            mDatabase = FirebaseDatabase.getInstance().getReference();
                            mDatabase.child("users").child(currentUser.getUid()).setValue(user);

                            finish();
                            //sendEmailVerification();



                        }

                    }
                });
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = mUser.getText().toString();

        if (TextUtils.isEmpty(email)) {
            mUser.setError("Required.");
            valid = false;
        } else {
            mUser.setError(null);
        }

        String password = mPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPassword.setError("Required.");
            valid = false;
        } else {
            mPassword.setError(null);
        }

        String fName = firstName.getText().toString();
        String sName = familyName.getText().toString();
        String tel = phoneNumber.getText().toString();

        if (TextUtils.isEmpty(fName)) {
            firstName.setError("Required.");
            valid = false;
        } else {
            firstName.setError(null);
        }

        if (TextUtils.isEmpty(sName)) {
            familyName.setError("Required.");
            valid = false;
        } else {
            familyName.setError(null);
        }

        if(TextUtils.isEmpty(tel)) {
            phoneNumber.setError("Required.");
            valid = false;
        } else {
            phoneNumber.setError(null);
        }

        return valid;
    }

    private void sendEmailVerification() {
        final FirebaseUser user = mAuth.getCurrentUser();

        user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegistrationForm.this,
                                    "Verification email sent to " + user.getEmail(),
                                    Toast.LENGTH_SHORT).show();

                        } else {
                            Log.e(TAG, "sendEmailVerification", task.getException());
                            Toast.makeText(RegistrationForm.this,
                                    "Failed to send verification email.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}
