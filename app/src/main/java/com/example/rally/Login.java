package com.example.rally;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    EditText etEmail;
    EditText etPassword;
    Button btSignIn;
    private FirebaseAuth mAuth;
    public static final String TAG = "LoginActivity";

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btSignIn = (Button)findViewById(R.id.btSignIn);
        mAuth = FirebaseAuth.getInstance();
        btSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = etEmail.getText().toString();
                final String password = etPassword.getText().toString();

                if (!email.isEmpty() && !password.isEmpty()) {
                    mAuth.signInWithEmailAndPassword(email, password).
                            addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Log.d(TAG, "successfully signed in:" + mAuth.getCurrentUser().getEmail());
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        updateUI(user);

                                    } else {
                                        Log.w(TAG, "could not successfullly login the user with the email: " + email);
                                        Toast.makeText(Login.this, "Could not sign in this user. Make sure your information is correct",
                                                Toast.LENGTH_SHORT).show();
                                        updateUI(null);

                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG,"Sign in failed "+e.getLocalizedMessage());
                        }
                    });
                    //etEmail.setText("");
                    //.setText("");
                }
                else
                {
                    if (email.isEmpty() && password.isEmpty())
                    {
                        Toast.makeText(Login.this, "PLease enter an email and password", Toast.LENGTH_SHORT).show();
                    }
                    else if(email.isEmpty())
                    {
                        Toast.makeText(Login.this, "Please enter an email", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(Login.this, "Please enter a password", Toast.LENGTH_SHORT).show();

                    }
                }
            }

        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);

    }

    private void updateUI(FirebaseUser currentUser) {
        if(currentUser != null){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }


}
