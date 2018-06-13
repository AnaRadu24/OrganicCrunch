package com.dragonbyte.unitycom.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dragonbyte.unitycom.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    //buttons
    Button infoButton;
    Button forgottenPassword;
    Button register;
    Button logButton;

    //editTexts
    EditText strEmail;
    EditText strPass;



    private static final String TAG = "EmailPassword";
    private static final String LOG_IN_SP = "LogIn";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences prefs = getSharedPreferences(LOG_IN_SP, MODE_PRIVATE);
        mAuth = FirebaseAuth.getInstance();
        String savedUsername = prefs.getString("username", null);
        String savedPassword = prefs.getString("password", null);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null) {
                    Log.e("", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    Log.e("", "onAuthStateChanged:signed_out");
                }
            }
        };
       // Log.e("here", savedUsername);
       // Log.e("here", savedPassword);
        if (savedUsername != null && savedPassword != null && savedUsername != "" && savedPassword != "") {
            Toast.makeText(getBaseContext(), "Please wait...", Toast.LENGTH_SHORT).show();
            signIn(savedUsername, savedPassword, false);
        }

        logButton = (Button) findViewById(R.id.login);
        strEmail = (EditText) findViewById(R.id.username);
        strPass = (EditText) findViewById(R.id.password);

        logButton.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        String email;
                        String password;

                        email = strEmail.getText().toString();
                        password = strPass.getText().toString();

                        Toast.makeText(getBaseContext(), "Trying to log in...", Toast.LENGTH_SHORT).show();
                        signIn(email, password, true);
                    }
                }
        );

        register = (Button) findViewById(R.id.registration);

        register.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        Intent startRegistration = new Intent(view.getContext(), RegistrationForm.class);
                        startActivity(startRegistration);
                    }
                }
        );

        infoButton = (Button) findViewById(R.id.information_button);

        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(v.getContext(), InfoApp.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if(mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void signIn(String email, String password, boolean type) {
        Log.e(TAG, "signIn: " + email);
        if(type) {
            if (!validateForm()) {
                Toast.makeText(MainActivity.this, "Log in failed.", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                            Toast.makeText(MainActivity.this, "Authentification failed!",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // [START_EXCLUDE]
                        if (task.isSuccessful()) {
                            Log.e("here", "here");
                            String mEmail = strEmail.getText().toString();
                            String mPass = strPass.getText().toString();
                            if (mEmail != "" && mPass!= "") {
                                SharedPreferences prefs = getSharedPreferences(LOG_IN_SP, MODE_PRIVATE);
                                SharedPreferences.Editor editor = prefs.edit();
                                editor.putString("username", mEmail);
                                editor.putString("password", mPass);
                                editor.commit();
                            }
                            strEmail.setText("");
                            strPass.setText("");
                            Intent intentToStartMainActivity = new Intent(getApplicationContext(), UnityComMainActivity.class);
                            startActivity(intentToStartMainActivity);
                            finish();
                        }
                        // [END_EXCLUDE]
                    }
                });

    }

    private boolean validateForm() {
        boolean valid = true;

        String email = strEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            strEmail.setError("Required.");
            valid = false;
        } else {
            strEmail.setError(null);
        }

        String password = strPass.getText().toString();
        if (TextUtils.isEmpty(password)) {
            strPass.setError("Required.");
            valid = false;
        } else {
            strPass.setError(null);
        }

        return valid;
    }
}
